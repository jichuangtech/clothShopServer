package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.constant.ResponseCode;
import com.jichuangtech.clothshopserver.model.LoginInfo;
import com.jichuangtech.clothshopserver.model.RegisterInfo;
import com.jichuangtech.clothshopserver.model.UserInfo;
import com.jichuangtech.clothshopserver.model.UsersEntity;
import com.jichuangtech.clothshopserver.model.vo.UsersVO;
import com.jichuangtech.clothshopserver.repository.UsersRepository;
import com.jichuangtech.clothshopserver.utils.DomainCopyUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.omg.CORBA.PUBLIC_MEMBER;
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

    public void refreshLoginInfo(UsersEntity entity,
                                 HttpServletRequest httpServletRequest,
                                 UserInfo userInfo) {
        String ip = httpServletRequest.getRemoteAddr();
        String host = httpServletRequest.getRemoteHost();

        entity.setLastIp(ip);
        entity.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        entity.setNickname(userInfo.nickName);
        entity.setHeadPic(userInfo.avatarUrl);
        entity.setloginCount(entity.getloginCount() + 1);
        usersRepository.save(entity);

        LOGGER.info(" refreshLoginInfo ip: " + ip + ", host: " + host + ", nickname: "  + userInfo.nickName);
    }

    private UsersEntity getUserByOpenId(String openId) {
        UsersEntity entity = usersRepository.findByOpenid(openId);
        LOGGER.info(" getUserByOpenId entity: " + entity + ", openId: " + openId);
        return entity;
    }

    private UsersEntity saveUserByOpenId(String openId) {
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

    public int login(LoginInfo info) {
        String mobile = info.getMobile();
        String password = info.getPassword();

        int code = ResponseCode.CODE_SUCCESS;
        if(usersRepository.findByMobile(mobile) == null) {
            code = ResponseCode.CODE_USER_NOT_FOUND;
        } else if(usersRepository.findByMobileAndPassword(mobile, password) == null) {
            code = ResponseCode.CODE_PASSWORD_WRONG;
        }
        return code;
    }

    public int register(RegisterInfo info) {
        String mobile = info.getMobile();
        String password = info.getPassword();

        int code = ResponseCode.CODE_SUCCESS;
        if(usersRepository.findByMobile(mobile) != null) {
            code = ResponseCode.CODE_USER_ALREADY_EXIST;
        } else {
            UsersEntity user = saveUserByOpenId("");
            user.setMobile(mobile);
            user.setOpenid(getAppUserOpenId(user));
            user.setPassword(password);
            LOGGER.info(" register user: " + user);
        }
        LOGGER.info(" register code: " + code);
        return code;
    }

    public UsersEntity getUserByMobile(String mobile) {
        return usersRepository.findByMobile(mobile);
    }

    private String getAppUserOpenId(UsersEntity entity) {
        return String.valueOf(entity.getUserId());
    }


}
