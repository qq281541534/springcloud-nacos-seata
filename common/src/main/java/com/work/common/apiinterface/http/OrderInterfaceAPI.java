package com.work.common.apiinterface.http;

import com.work.common.component.RespBody;
import com.work.common.dto.OrderDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author liuyu
 * @Description 订单接口
 * @createTime 2022/1/13 23:29 
 */
public interface OrderInterfaceAPI {

    /**
     * @title 创建订单
     * @description 
     * @author liuyu 
     * @updateTime 2022/1/13 23:30 
     * @throws
     */
    @PostMapping("/order/createOrder")
    RespBody<OrderDTO> createOrder(OrderDTO orderDTO);
}
