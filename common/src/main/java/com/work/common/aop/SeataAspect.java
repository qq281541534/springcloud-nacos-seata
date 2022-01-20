package com.work.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.work.common.component.RespBody;
import com.work.common.component.RespCode;
import io.seata.core.exception.GlobalTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * <p>说明：</p>
 * <li>
 * 1. 本系统中，controller已经catch了大部分异常，剩余的异常已经被{@link GlobalExceptionHandler#exceptionHandler }
 * 做了统一拦截，所以，本aop旨在对服务间的调用异常返回数据（RespBody）做统一处理
 * </li>
 * <li>
 * 2. 在feign的调用中，没有对feign的返回值做要求，所以feign的返回值可以为任何形式，也可以为null。
 * 同理，在fallback中也可能返回值是void，在此时@AfterReturning方法中的returnValue参数便可能为空，但是可能client可能是正常执行。<b>此处应该做判定</b>
 * </li>
 * <li>
 * 3: 注意clientPointcut和fallbackPointcut的配置，否则可能找不到具体的方法从而导致回滚失败
 * </li>
 *
 */
@Component
@Aspect
@Slf4j
public class SeataAspect {


    /**
     * 约定配置，切面为*Client的feign客户端。
     */
    @Pointcut("execution(* com.work.*.feign.*.*(..))")
    public void clientPointcut() {
    }

    /**
     * 配置熔断路径，若无，可以不配
     */
    @Pointcut("execution(* com.work.*.feign.fallback.*.*(..))")
    public void fallbackPointcut() {
    }


    @AfterReturning(value = "clientPointcut()", returning = "returnValue")
    public void globalTransactionAfter(JoinPoint jp, RespBody returnValue) throws Throwable {
        if (jp.getSignature() instanceof MethodSignature) {
            Method method = ((MethodSignature) jp.getSignature()).getMethod();
            log.info("method name：" + method.getName());
            Type genericReturnType = method.getGenericReturnType();
            if ("void".equals(genericReturnType.getTypeName())) {
                return;
            }
        }

        log.info("后置feign：切面参数：{}", JSONObject.toJSONString(jp.getArgs()));
        log.info("后置feign：切面返回参数：{}", returnValue.toString());
        if (null == returnValue || RespCode.SUCCESS.getCode() != returnValue.getStatus()) {
            throw new GlobalTransactionException("远程调用未返回逾期结果，抛出全局事务异常，回滚");
        }
    }

    @AfterReturning(value = "fallbackPointcut()", returning = "returnValue")
    public void doRecoveryActions(Object returnValue) throws GlobalTransactionException {
        log.info("执行异常的方法为:{}", JSONObject.toJSONString(returnValue));
        throw new GlobalTransactionException("fallbackPointcut................*******");
    }
}