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
    public void save() {


        UserInfoPo first = new UserInfoPo();
        first.setUsername("test");
        first.setAccountType("seller");
        first.setFirstNAME("test1");
        first.setLastNAME("lastName");
        first.setBirthday("2022-07-06");
        first.setEmail("abc@gmail.con");
        first.setMobileNo("0900123456");
        first.setPassword("123456");

        UserInfoPo user = userInfoDao.save(first);
        List<UserInfoPo> users = (List<UserInfoPo>) userInfoDao.findAll();

        Assertions.assertEquals(1, users.size());

    }




}
