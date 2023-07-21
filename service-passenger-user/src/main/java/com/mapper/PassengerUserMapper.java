package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dto.PassengerUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author Administrator
* @description 针对表【passenger_user(乘客用户表)】的数据库操作Mapper
* @createDate 2023-07-21 19:01:29
* @Entity generator.domain.PassengerUser
*/
@Mapper
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {

}




