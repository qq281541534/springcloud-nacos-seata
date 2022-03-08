package com.work.business.service;

import com.work.business.feign.OrderFeignClient;
import com.work.business.feign.StockFeignClient;
import com.work.common.component.RespBody;
import com.work.common.component.RespCode;
import com.work.common.dto.BusinessDTO;
import com.work.common.dto.CommodityDTO;
import com.work.common.dto.OrderDTO;
import com.work.common.exception.DefaultException;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BusinessService {

    private final StockFeignClient stockFeignClient;

    private final OrderFeignClient orderFeignClient;

    boolean flag;

    /**
     * 处理业务逻辑 正常的业务逻辑
     *
     * @Param:
     * @Return:
     */
    @GlobalTransactional(timeoutMills = 300000, name = "gts-business-seata")
    public RespBody handleBusiness(BusinessDTO businessDTO) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        RespBody<Object> respBody = new RespBody<>();
        //1、扣减库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        RespBody stockResponse = stockFeignClient.decreaseStock(commodityDTO);
        //2、创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        RespBody<OrderDTO> response = orderFeignClient.createOrder(orderDTO);

        if (stockResponse.getStatus() != 200 || response.getStatus() != 200) {
            throw new DefaultException(RespCode.FAIL);
        }

        respBody.setStatus(RespCode.SUCCESS.getCode());
        respBody.setMessage(RespCode.SUCCESS.getMessage());
        respBody.setData(response.getData());
        return respBody;
    }

    /**
     * 出处理业务服务，出现异常回顾
     *
     * @param businessDTO
     * @return
     */
    @GlobalTransactional(timeoutMills = 300000, name = "gts-business-seata-rollback")
    public RespBody handleBusiness2(BusinessDTO businessDTO) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        //1、扣减库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        RespBody stockResp = stockFeignClient.decreaseStock(commodityDTO);
        //2、创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        RespBody<OrderDTO> orderResp = orderFeignClient.createOrder(orderDTO);
        //  打开注释测试事务发生异常后，全局回滚功能
        if (!flag) {
            throw new DefaultException(RespCode.FAIL);
        }

        return new RespBody(orderResp.getData());
    }
}