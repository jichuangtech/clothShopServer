package com.jichuangtech.clothshopserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jichuangtech.clothshopserver.model.Response;

/**
 * Created by yangjb on 2017/8/19.
 * helloWorld
 */
@ControllerAdvice
public class ExceptionCatch {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response<String> handleMaxUploadException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        Response<String> dataResp = new Response<String>();
        dataResp.statusCode = 500;
        dataResp.msg = "error occur.more info see backend";
        return dataResp;
    }
}
