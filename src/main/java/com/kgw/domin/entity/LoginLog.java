package com.kgw.domin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kgw.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/28 19:14
 */
@Data
@Accessors(chain = true)
@TableName("log_login_log")
public class LoginLog {

    @TableId(type = IdType.AUTO)
    private Long loginId;

    private String adminName;

    private String requestIp;

    private String LoginAddress;

    private String broswerName;

    private String osName;

    private int loginStatus;

    private String message;

    private LocalDateTime loginTime;

}
