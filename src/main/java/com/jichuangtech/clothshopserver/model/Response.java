package com.jichuangtech.clothshopserver.model;

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
    public String msg;

    /**
     * 用来返回各种数据
     */
    public T data;
}
