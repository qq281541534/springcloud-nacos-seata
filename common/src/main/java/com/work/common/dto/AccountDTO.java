package com.work.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {

    private Integer id;
    private String userId;
    private BigDecimal amount;

}