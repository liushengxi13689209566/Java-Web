package com.huarun.controller;

import com.huarun.pojo.BasDict;
import com.huarun.service.BasDictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basdict")
public class BasDictController {
    //controller调用service层
    //@Autowired
    //@Qualifier("OrderLineServiceImpl")
    private BasDictService basDictService;

    //增
    @RequestMapping("/addBasDict")
    public String addBasDict(BasDict basDict) {
        basDictService.addBasDict(basDict);
        return "redirect:/basdict/allBasDict";
    }

    //删
    @RequestMapping("/deleteBasDictById")
    public String deleteBasDictById(int id) {
        basDictService.deleteBasDictById(id);
        return "redirect:/basdict/allBasDict";
    }

    //改
    @RequestMapping("/updateBasDict")
    public String updateBasDict(Model model,BasDict basDict) {
        basDictService.updateBasDict(basDict);
        BasDict basDict1 = basDictService.queryBasDictById(basDict.getDictID());
        model.addAttribute("BasDict", basDict1);
        return "redirect:/basdict/allBasDict";
    }

    //查
    @RequestMapping("/allBasDict")
    public String list(Model model) {
        List<BasDict> list = basDictService.queryAllBasDict();
        model.addAttribute("list", list);
        return "allBasDict";
    }
}
