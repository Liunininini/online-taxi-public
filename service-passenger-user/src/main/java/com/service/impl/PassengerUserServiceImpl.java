package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.dto.PassengerUser;
import com.mapper.PassengerUserMapper;
import com.service.PassengerUserService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【passenger_user(乘客用户表)】的数据库操作Service实现
* @createDate 2023-07-21 19:01:29
*/
@Service
public class PassengerUserServiceImpl extends ServiceImpl<PassengerUserMapper, PassengerUser> implements PassengerUserService {

}




