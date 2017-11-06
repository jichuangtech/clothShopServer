package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.service.SessionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangjb on 2017/8/23.
 * helloWorld
 */
@RestController
public class LoginController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private HttpServletRequest httpServletRequest;

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
            String id = httpServletRequest.getSession().getId();
            sessionService.put(id, id);
            return id;
        }
        return "no";
    }
}
