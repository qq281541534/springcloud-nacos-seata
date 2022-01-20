package com.work.business.feign;

import com.work.common.apiinterface.StockInterfaceAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "stock-service")
public interface StockFeignClient extends StockInterfaceAPI {

}
