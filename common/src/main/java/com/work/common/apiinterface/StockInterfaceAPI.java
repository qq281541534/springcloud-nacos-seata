package com.work.common.apiinterface;

import com.work.common.component.RespBody;
import com.work.common.dto.CommodityDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author liuyu
 * @Description 库存接口
 * @createTime 2022/1/13 23:26
 */
public interface StockInterfaceAPI {

    /**
     * @title 扣减库存
     * @description
     * @author liuyu
     * @updateTime 2022/1/13 23:26
     * @throws
     */
    @PostMapping("/stock/decStock")
    RespBody decreaseStock(CommodityDTO commodityDTO);
}
