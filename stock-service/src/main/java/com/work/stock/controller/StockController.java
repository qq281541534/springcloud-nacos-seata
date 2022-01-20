package com.work.stock.controller;

import com.work.common.apiinterface.StockInterfaceAPI;
import com.work.common.component.RespBody;
import com.work.common.dto.CommodityDTO;
import com.work.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController implements StockInterfaceAPI {

    private final StockService stockService;

    /**
     * 扣减库存
     */
    @Override
    @PostMapping("decStock")
    public RespBody decreaseStock(@RequestBody CommodityDTO commodityDTO) {
        log.info("请求库存微服务：{}", commodityDTO.toString());
        return stockService.decreaseStock(commodityDTO);
    }

}
