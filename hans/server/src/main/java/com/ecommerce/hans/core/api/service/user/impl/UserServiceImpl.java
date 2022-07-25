package com.ecommerce.hans.core.api.service.user.impl;

import com.ecommerce.hans.common.dao.UserInfoDao;
import com.ecommerce.hans.common.entity.UserInfoPo;
import com.ecommerce.hans.core.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {


    @Override
    public Optional<UserInfoPo> findByUsername(String username) {
        return Optional.empty();
    }
}
