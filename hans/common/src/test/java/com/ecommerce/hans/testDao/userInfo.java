package com.ecommerce.hans.testDao;

import com.ecommerce.hans.dao.UserInfoDao;
import com.ecommerce.hans.entity.UserInfoPo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@SpringBootTest
@Transactional
public class userInfo {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void findByUsername() {


        UserInfoPo first = new UserInfoPo();
        first.setUsername("test");
        first.setAccountType("seller");
        first.setFirstNAME("test1");
        first.setLastNAME("lastName");
        first.setBirthday("2022-07-06");
        first.setEmail("abc@gmail.con");
        first.setMobileNo("0900123456");
        first.setPassword("123456");

        UserInfoPo user1 = userInfoDao.save(first);
        List<UserInfoPo> users = userInfoDao.findAll();
        UserInfoPo user = userInfoDao.findByUsername(first.getUsername());

        Assertions.assertEquals("test", user1.getUsername());

    }




}
