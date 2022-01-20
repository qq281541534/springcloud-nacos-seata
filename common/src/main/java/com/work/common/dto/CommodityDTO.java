package com.work.common.dto;

import lombok.Data;

@Data
public class CommodityDTO {

    private Integer id;
    private String commodityCode;
    private String name;
    private Integer count;

}