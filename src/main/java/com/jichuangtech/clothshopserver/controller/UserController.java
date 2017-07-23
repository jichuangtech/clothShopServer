package com.jichuangtech.clothshopserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Bingo on 2017/7/21.
 */

@Controller
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        System.out.println("我进行访问首页");
        return "index";
    }
}
