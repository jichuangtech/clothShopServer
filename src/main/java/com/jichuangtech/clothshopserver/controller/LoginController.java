package com.jichuangtech.clothshopserver.controller;

import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.Response;
import com.jichuangtech.clothshopserver.model.Token;
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
    public Response<Token> loginFromCMS(String username, String password) {
        Response<Token> resp = new Response();

        if (StringUtils.equalsIgnoreCase("admin", username) && StringUtils.equalsIgnoreCase("admin", password)) {
            String id = httpServletRequest.getSession().getId();
            sessionService.putWx(id, id);
            resp.data = new Token(id);
        } else {
            resp.setStatusCode(ResponseCode.CODE_LOGIN_CMS_ERROR);
        }
        return resp;
    }
}
