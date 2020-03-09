package com.huarun.service;

import com.huarun.pojo.OrderLine;

import java.util.List;

public interface OrderLineService {
    //增
    int addOrderLine(OrderLine orderLine);

    //删
    int deleteOrderLineById(int id);

    //改
    int updateOrderLine(OrderLine orderLine);

    //查
    OrderLine queryOrderLineById(int id);

    //查询全部
    List<OrderLine> queryAllOrderLine();
}
