package com.huarun.controller;

import com.huarun.pojo.ProStorage;
import com.huarun.service.ProStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/prostorage")
public class ProStorageController {
    //controller调用service层
    @Autowired
    @Qualifier("ProStorageServiceImpl") //这里应该是有问题的？？
    private ProStorageService proStorageService;

    //增
    @RequestMapping("/addProStorage")
    public String addProStorage(ProStorage proStorage) {
        //System.out.println(proStorage);
        proStorageService.addProStorage(proStorage);
        return "redirect:/prostorage/allProStorage";
    }

    //删
    @RequestMapping("/deleteProStorageById")
    public String deleteProStorageById(int id) {
        proStorageService.deleteProStorageById(id);
        return "redirect:/prostorage/allProStorage";
    }

    //改
    @RequestMapping("/updateProStorage")
    public String updateProStorage(Model model, ProStorage proStorage) {
        proStorageService.updateProStorage(proStorage);
        ProStorage proStorage1 = proStorageService.queryProStorageById(proStorage.getStkID());
        model.addAttribute("ProStorage", proStorage1);
        return "redirect:/prostorage/allProStorage";
    }

    //查
    @RequestMapping("/allProStorage")
    public String list(Model model) {
        List<ProStorage> list = proStorageService.queryAllProStorage();
        model.addAttribute("list", list);
        return "allProStorage";
    }
}
