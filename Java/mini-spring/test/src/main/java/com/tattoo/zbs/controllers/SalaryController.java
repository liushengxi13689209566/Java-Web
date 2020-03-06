package com.tattoo.zbs.controllers;

import com.tattoo.zbs.web.mvc.Controller;
import com.tattoo.zbs.web.mvc.RequestMapping;
import com.tattoo.zbs.web.mvc.RequestParam;

@Controller
public class SalaryController {
    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam("workage") String workage) {
        return 10000;
    }
}
