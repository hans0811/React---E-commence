package com.ecommerce.hans.common.dao;

import com.ecommerce.hans.common.entity.UserInfoPo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends BaseDao<UserInfoPo, Long> {
    UserInfoPo findByUsername(String username);

}
