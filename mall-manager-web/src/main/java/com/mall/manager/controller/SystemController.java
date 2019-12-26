package com.mall.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zll on 2019/12/24.
 * 系统相关业务
 */
@Controller
public class SystemController {

    @RequestMapping("/")//首页
    public String index(){
        return "index";
    }

    @RequestMapping("/system/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
