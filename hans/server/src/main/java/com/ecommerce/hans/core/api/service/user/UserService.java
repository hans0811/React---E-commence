package com.ecommerce.hans.core.api.service.user;

import com.ecommerce.hans.common.entity.UserInfoPo;

import java.util.Optional;

public interface UserService {

    Optional<UserInfoPo> findByUsername(String username);
}
