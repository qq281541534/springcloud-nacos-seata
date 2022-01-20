package com.work.stock.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuyu
 * @Description 库存
 * @createTime 2022/1/13 22:55
 */
@Data
@Accessors(chain = true)
@TableName("stock_tbl")
public class Stock {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String commodityCode;
    private String name;
    private Integer count;

}
