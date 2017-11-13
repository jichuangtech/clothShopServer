package com.jichuangtech.clothshopserver.utils;

import org.apache.commons.lang.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Bingo on 2017/11/12.
 */
public class IpUtils {

    /**
     * IpUtils工具类方法
     * 获取真实的ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(org.apache.commons.lang.StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(org.apache.commons.lang.StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
