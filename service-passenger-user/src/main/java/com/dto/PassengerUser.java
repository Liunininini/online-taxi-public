package com.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 乘客用户表
 * @TableName passenger_user
 */
@TableName(value ="passenger_user")
@Data
public class PassengerUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /**
     * 更新时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /**
     * 乘客手机号
     */
    private String passengerPhone;

    /**
     * 乘客名称
     */
    private String passengerName;

    /**
     * 乘客性别:0女,1男
     */
    private Integer passengerGender;

    /**
     * 乘客状态:0有效,1无效
     */
    private Integer state;

}