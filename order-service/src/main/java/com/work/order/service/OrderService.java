package com.work.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.common.component.RespBody;
import com.work.common.component.RespCode;
import com.work.common.dto.AccountDTO;
import com.work.common.dto.OrderDTO;
import com.work.order.feign.AccountFeignClient;
import com.work.order.model.Order;
import com.work.order.repository.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    private final AccountFeignClient accountFeignClient;

    /**
     * 创建订单
     *
     * @Param: OrderDTO  订单对象
     * @Return: OrderDTO  订单对象
     */
    public RespBody<OrderDTO> createOrder(OrderDTO orderDTO) {
        //扣减用户账户
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        RespBody accountResp = accountFeignClient.decreaseAccount(accountDTO);

        //生成订单号
        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        //生成订单
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setCount(orderDTO.getOrderCount());
        order.setAmount(orderDTO.getOrderAmount().doubleValue());
        try {
            baseMapper.createOrder(order);
        } catch (Exception e) {
            return new RespBody<>(RespCode.FAIL);
        }

        if (accountResp.getStatus() != 200) {
            return new RespBody<>(RespCode.FAIL);
        }
        return new RespBody<>(RespCode.SUCCESS);
    }

}
