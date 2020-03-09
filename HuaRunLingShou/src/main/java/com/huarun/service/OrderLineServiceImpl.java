package com.huarun.service;

import com.huarun.dao.OrderLineMapper;
import com.huarun.pojo.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderLineServiceImpl implements OrderLineService {
    @Autowired
    // service 层调用 dao 层: 组合dao层
    private OrderLineMapper orderLineMapper;

    public void setOrderLineMapper(OrderLineMapper orderLineMapper) {
        this.orderLineMapper = orderLineMapper;
    }

    @Override
    public int addOrderLine(OrderLine orderLine) {
        return orderLineMapper.addOrderLine(orderLine);
    }

    @Override
    public int deleteOrderLineById(int id) {
        return orderLineMapper.deleteOrderLineById(id);
    }

    @Override
    public int updateOrderLine(OrderLine orderLine) {
        return orderLineMapper.updateOrderLine(orderLine);
    }

    @Override
    public OrderLine queryOrderLineById(int id) {
        return orderLineMapper.queryOrderLineById(id);
    }

    @Override
    public List<OrderLine> queryAllOrderLine() {
        return orderLineMapper.queryAllOrderLine();
    }
}
