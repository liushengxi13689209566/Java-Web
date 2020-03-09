package com.tattoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello") //请求

    public String sayhello(Model model) {
        //封装数据
        model.addAttribute("msg", "liushnegxi ahhaha");

        return "hello";
    }

}
