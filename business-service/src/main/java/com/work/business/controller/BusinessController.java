package com.work.business.controller;

import com.work.business.service.BusinessService;
import com.work.common.component.RespBody;
import com.work.common.dto.BusinessDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    /**
     * 模拟用户购买商品下单业务逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("buy")
    RespBody handleBusiness(@RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    /**
     * 模拟用户购买商品下单业务逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy2")
    RespBody handleBusiness2(@RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness2(businessDTO);
    }
}