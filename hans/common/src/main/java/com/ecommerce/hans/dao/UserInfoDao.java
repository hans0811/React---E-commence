package com.ecommerce.hans.dao;

import com.ecommerce.hans.entity.UserInfoPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfoPo, Long> {

    Set<Integer> findById(Integer Id);

    UserInfoPo save(UserInfoPo userInfoPo);

}
