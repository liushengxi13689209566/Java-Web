package com.huarun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huarun.pojo.MajorDO;
import com.huarun.service.MajorService;
import com.huarun.utils.ResponseUtil;
import com.huarun.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/major")

public class MajorController {
    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "/getAllMajor", method = RequestMethod.GET)
    public void getAllMajor(HttpServletResponse response) throws Exception {
        List<MajorDO> list = majorService.getAllMajor();

        JSONObject result = new JSONObject();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));

        result.put("rows", array);
        result.put("status_code", StatusCode.SUCCESS);
        result.put("msg", "成功");

        System.out.println("getAllMajor result == " + result);
        ResponseUtil.write(response, result);
    }
}
