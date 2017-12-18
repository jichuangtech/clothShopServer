package com.jichuangtech.clothshopserver.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangjb on 2017/8/17.
 * 管理session
 */
@Service
public class SessionService {

    //微信小程序
    private static final int WX_TOKEN_EXPIRE_DURATION_MINUTES = 5;
    //移动端 android ios
    private static final int APP_TOKEN_EXPIRE_DURATION_DAYS = 7;

    private LoadingCache<String, String> session = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(WX_TOKEN_EXPIRE_DURATION_MINUTES, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                public String load(String key) {
                    return "";
                }
            });

    public void put(String key, String value) {
        session.put(key, value);
    }

    public String get(String key) {
        try {
            String value = session.get(key);
            if (value == null || TextUtils.isEmpty(value)) {
                return null;
            }
            //更新一下key的缓存时间
            put(key, value);
            return value;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
