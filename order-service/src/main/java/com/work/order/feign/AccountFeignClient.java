package com.work.order.feign;

import com.work.common.apiinterface.AccountInterfaceAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "account-service")
public interface AccountFeignClient extends AccountInterfaceAPI {

}
