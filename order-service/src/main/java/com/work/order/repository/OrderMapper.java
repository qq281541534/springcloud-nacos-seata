package com.work.order.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.order.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 创建订单
     *
     * @Param: order 订单信息
     * @Return:
     */
    void createOrder(@Param("order") Order order);
}
