package com.jichuangtech.clothshopserver.service;

import com.jichuangtech.clothshopserver.model.UsersEntity;
import com.jichuangtech.clothshopserver.model.vo.UsersVO;
import com.jichuangtech.clothshopserver.repository.UsersRepository;
import com.jichuangtech.clothshopserver.utils.DomainCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangjb on 2017/9/18.
 * 用户模块
 */
@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<UsersVO> list() {
        List<UsersEntity> all = usersRepository.findAll();
        return DomainCopyUtil.mapList(all, UsersVO.class);
    }
}
