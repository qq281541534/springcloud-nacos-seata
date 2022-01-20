package com.work.order.controller;

import com.work.common.apiinterface.OrderInterfaceAPI;
import com.work.common.component.RespBody;
import com.work.common.dto.OrderDTO;
import com.work.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController implements OrderInterfaceAPI {

    private final OrderService orderService;

    @Override
    @PostMapping("/createOrder")
    public RespBody<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("请求订单微服务：{}", orderDTO.toString());
        return orderService.createOrder(orderDTO);
    }
}
