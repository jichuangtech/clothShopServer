package com.jichuangtech.clothshopserver.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jichuangtech.clothshopserver.constant.Constant;
import org.apache.http.util.TextUtils;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangjb on 2017/8/17.
 * 管理session
 */
@Service
public class SessionService {
    private static final String TAG = SessionService.class.getSimpleName();
    private Logger mLogger = LoggerFactory.getLogger(Constant.MODULE_NAME);
    //微信小程序
    private static final int WX_TOKEN_EXPIRE_DURATION_MINUTES = 5;

    //CMS后台管理系统
    private static final int CMS_TOKEN_EXPIRE_DURATION_HOURS = 1;

    //移动端 android ios
    private static final int APP_TOKEN_EXPIRE_DURATION_DAYS = 7;

    private LoadingCache<String, String> mWxSession = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(WX_TOKEN_EXPIRE_DURATION_MINUTES, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                public String load(String key) {
                    return "";
                }
            });

    private LoadingCache<String, String> mAppSession = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(APP_TOKEN_EXPIRE_DURATION_DAYS, TimeUnit.DAYS)
            .build(new CacheLoader<String, String>() {
                public String load(String key) {
                    return "";
                }
            });

    public void putWx(String key, String value) {
        mWxSession.put(key, value);
    }

    public void removeAppItem(String key) {
        mAppSession.invalidate(key);
    }

    public void putApp(String key, String value) {
        mAppSession.put(key, value);
    }

    public String get(String key) {

        String openId = getOpenId(mWxSession, key);
        if(openId == null) {
            openId = getOpenId(mAppSession, key);
            mLogger.info(TAG, "get key from mAppSession...");
        }
        mLogger.info(TAG, "get key: " + key + "openId: " + openId);
        return openId;
    }

    private String getOpenId(LoadingCache<String, String> session, String key) {
        try {
            String value = session.get(key);
            if (value == null || TextUtils.isEmpty(value)) {
                return null;
            }
            //更新一下key的缓存时间
            putWx(key, value);
            return value;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
