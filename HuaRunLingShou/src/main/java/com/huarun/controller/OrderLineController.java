package com.huarun.controller;

import com.huarun.pojo.OrderLine;
import com.huarun.pojo.ProStorage;
import com.huarun.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orderline")
public class OrderLineController {
    @Autowired
    //controller调用service层
    @Qualifier("OrderLineServiceImpl")
    private OrderLineService orderLineService;

    //增
    public String addOrderLine(OrderLine orderLine) {
        orderLineService.addOrderLine(orderLine);
        return "redirect:/orderline/allOrderLine";
    }

    //删
    @RequestMapping("/deleteOrderLineById")
    public String deleteOrderLineById(int id) {
        orderLineService.deleteOrderLineById(id);
        return "redirect:/orderline/allOrderLine";
    }

    //改
    @RequestMapping("/updateOrderLine")
    public String updateOrderLine(Model model, OrderLine orderLine) {
        orderLineService.updateOrderLine(orderLine);
        OrderLine orderLine1 = orderLineService.queryOrderLineById(orderLine.getOddID());
        model.addAttribute("OrderLine", orderLine1);
        return "redirect:/orderline/allOrderLine";
    }

    //查
    @RequestMapping("/allOrderLine")
    public String list(Model model) {
        List<OrderLine> list = orderLineService.queryAllOrderLine();
        model.addAttribute("list", list);
        return "allOrderLine";
    }
}
