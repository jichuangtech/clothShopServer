package com.jichuangtech.clothshopserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bingo on 2017/7/21.
 */
@Api(description = "用户模块接口")
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * @param code 小程序编码
     * @return
     */
    @ApiOperation(value = "小程序登录", notes = "必须要传code过来,根据这个code,服务器这边会生成一个session_key返回。当做身份验证")
    @RequestMapping(value = "/onlogin", method = RequestMethod.GET)
    public String onlogin(@ApiParam(name = "验证码") String code) {
        LOGGER.info("log test------------------");
        return "index";
    }
}
