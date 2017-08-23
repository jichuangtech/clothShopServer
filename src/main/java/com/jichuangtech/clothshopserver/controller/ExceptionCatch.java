package com.jichuangtech.clothshopserver.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangjb on 2017/8/19.
 * helloWorld
 */
@ControllerAdvice
public class ExceptionCatch {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handleMaxUploadException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        return "error occur.more info see backend";
    }
}
