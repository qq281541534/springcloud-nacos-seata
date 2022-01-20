package com.work.stock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.common.component.RespBody;
import com.work.common.component.RespCode;
import com.work.common.dto.CommodityDTO;
import com.work.stock.model.Stock;
import com.work.stock.repository.StockMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class StockService extends ServiceImpl<StockMapper, Stock> {

    public RespBody decreaseStock(CommodityDTO commodityDTO) {
        int stock = baseMapper.decreaseStock(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        if (stock > 0) {
            return new RespBody<>(RespCode.SUCCESS);
        }
        return new RespBody<>(RespCode.FAIL);
    }
}
