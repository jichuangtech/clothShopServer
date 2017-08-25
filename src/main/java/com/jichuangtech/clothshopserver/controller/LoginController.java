package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.service.SessionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangjb on 2017/8/23.
 * helloWorld
 */
@RestController
public class LoginController {
    @Autowired
    private SessionService sessionService;

    /**
     * 后台管理系统登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getByOrderStatus(String username, String password) {
        if (StringUtils.equalsIgnoreCase("admin", username) && StringUtils.equalsIgnoreCase("admin", password)) {
            sessionService.put("123456", "123");
            return "123456";
        }
        return "no";
    }
}
