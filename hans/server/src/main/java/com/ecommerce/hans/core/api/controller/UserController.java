package com.ecommerce.hans.core.api.controller;

import com.ecommerce.hans.dao.UserInfoDao;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "userInfo")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoDao userInfoDao;




}
