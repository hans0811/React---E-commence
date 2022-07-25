package com.ecommerce.hans.core.api.controller;

import com.ecommerce.hans.core.api.controller.bean.UserInfoVo;
import com.ecommerce.hans.core.api.controller.support.ServiceRequest;
import com.ecommerce.hans.core.api.controller.support.ServiceResponse;
import com.ecommerce.hans.core.api.controller.support.ServiceResponseBuilder;
import com.ecommerce.hans.core.api.controller.support.UserContext;
import com.ecommerce.hans.common.dao.UserInfoDao;
import com.ecommerce.hans.common.entity.UserInfoPo;
import com.ecommerce.hans.common.utils.BeanCopyUtils;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(description = "userInfo")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoDao userInfoDao;



    //@ApiOperation("Get current user info")
    @PostMapping("/view")
    public ServiceResponse<UserInfoPo> getUser(@RequestBody ServiceRequest<Null> serviceRequest){

        final UserInfoPo userInfo = userInfoDao.findByUsername("");
//        final UserInfoVo user = new UserInfoVo();
//
//        BeanCopyUtils.copyProperties(userInfo, user);

        return ServiceResponseBuilder.build(userInfo);

    }

    @PostMapping("/hello")
    public String hello(){

        return "hello";
    }





}
