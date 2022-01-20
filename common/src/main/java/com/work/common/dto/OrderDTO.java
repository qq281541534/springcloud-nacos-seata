package com.work.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {

    private String orderNo;
    private String userId;
    private String commodityCode;
    private Integer orderCount;
    private BigDecimal orderAmount;

}