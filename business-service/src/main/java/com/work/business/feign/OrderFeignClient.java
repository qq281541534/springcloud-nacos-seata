package com.work.business.feign;

import com.work.common.apiinterface.http.OrderInterfaceAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author liuyu
 * @Description 订单接口
 * @createTime 2022-01-13- 23:24:00
 */
@FeignClient(name = "order-service")
public interface OrderFeignClient extends OrderInterfaceAPI {

}
