package com.huarun.controller;

import com.huarun.service.Sys_RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys_role")
public class Sys_RoleController {

    @Autowired
    @Qualifier("Sys_RoleServiceImpl")
    private Sys_RoleService sys_roleService;


}
