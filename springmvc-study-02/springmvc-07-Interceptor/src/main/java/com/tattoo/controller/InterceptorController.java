package com.tattoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InterceptorController {
    @RequestMapping("/interceptor")
    @ResponseBody
    public String testFunction(Model model) {
        model.addAttribute("msg", "执行成功");
        System.out.println("控制器中的方法执行了");
        return "hello";
    }
}