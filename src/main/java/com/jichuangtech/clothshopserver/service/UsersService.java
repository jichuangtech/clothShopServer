package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.model.UsersEntity;
import com.jichuangtech.clothshopserver.model.vo.UsersVO;
import com.jichuangtech.clothshopserver.repository.UsersRepository;
import com.jichuangtech.clothshopserver.utils.DomainCopyUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yangjb on 2017/9/18.
 * 用户模块
 */
@Service
public class UsersService {
    private static final String TAG = UsersService.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(TAG);
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SessionService sessionService;

    public List<UsersVO> list() {
        List<UsersEntity> all = usersRepository.findAll();
        return DomainCopyUtil.mapList(all, UsersVO.class);
    }

    public UsersEntity validateUser(String openId) {
        UsersEntity entity = getUserByOpenId(openId);
        LOGGER.info(" validateUser entity1: " + entity);
        if(entity == null) {
            LOGGER.info(" validateUser entity is null... ");
            entity = saveUserByOpenId(openId);
            LOGGER.info(" validateUser entity2: " + entity);
        }

        return entity;
    }

    public void refreshLoginInfo(UsersEntity entity, HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getRemoteAddr();
        String host = httpServletRequest.getRemoteHost();

        entity.setLastIp(ip);
        entity.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        usersRepository.save(entity);

        LOGGER.info(" refreshLoginInfo ip: " + ip + ", host: " + host);
    }

    public UsersEntity getUserByOpenId(String openId) {
        UsersEntity entity = usersRepository.findByOpenid(openId);
        LOGGER.info(" getUserByOpenId entity: " + entity + ", openId: " + openId);
        return entity;
    }

    public UsersEntity saveUserByOpenId(String openId) {
        UsersEntity entity = new UsersEntity();
        entity.setOpenid(openId);
        entity.setCraetedAt(new Timestamp(System.currentTimeMillis()));
        entity = usersRepository.save(entity);
        LOGGER.info(" saveUserByOpenId entity: " + entity + ", openId: " + openId);
        return entity;
    }

    public int getUserIdByToken(String token) {
        String openId  = sessionService.get(token);
        int userId = -1;
        UsersEntity entity = getUserByOpenId(openId);
        if(entity != null) {
            userId  = Math.toIntExact(entity.getUserId());
        }
        LOGGER.info(" getUserIdByToken entity: " + entity + ", openId: " + openId);
        return userId;
    }
}
