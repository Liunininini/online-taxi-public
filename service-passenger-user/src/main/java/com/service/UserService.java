package com.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.internalcommon.constant.CommonStatusEnum;
import com.example.internalcommon.dto.PassengerUser;
import com.example.internalcommon.dto.ResponseResult;
import com.mapper.PassengerUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Resource
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrReg(String passengerPhone) {
        //根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        log.info("passengerUsers is {}", JSON.toJSONString(passengerUsers));
        //判断用户是否存在
        if (CollectionUtils.isEmpty(passengerUsers)){
            //如果不存在,插入用户信息
            PassengerUser  user = new PassengerUser();
            user.setPassengerPhone(passengerPhone);
            user.setPassengerName("张三");
            user.setState(0);
            user.setPassengerGender(0);
            passengerUserMapper.insert(user);
            return ResponseResult.success(user);
        }
        return ResponseResult.success(passengerUsers.get(0));

    }

    public ResponseResult getUserByPhone(String passengerPhone){
        //根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if (CollectionUtils.isEmpty(passengerUsers)){
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXIST.getCode(),CommonStatusEnum.USER_NOT_EXIST.getValue());
        }
        return ResponseResult.success(passengerUsers.get(0));
    }

}
