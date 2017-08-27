package com.jichuangtech.clothshopserver.model;

import com.jichuangtech.clothshopserver.constant.ResponseCode;

/**
 * Created by Bingo on 2017/8/10.
 */
public class Response <T>{
    /**
     * 200是正确的 其他都是错误的
     */
    public int statusCode = 200;

    /**
     * 状态描述
     */
    public String msg = "success ...";

    /**
     * 用来返回各种数据
     */
    public T data;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        refreshMsg();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private void refreshMsg() {
        msg = ResponseCode.getMsg(statusCode);
    }
}
