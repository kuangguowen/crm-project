package com.kgw.domin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kgw.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author kgw
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_admin")
public class Admin extends BaseEntity {


    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 管理员昵称
     */
    private String nickName;

    /**
     * 管理员性别 0 : 男   1：女     2： 表示未知
     */
    private Integer gender;

    /**
     * 管理员手机
     */
    private String adminPhone;

    /**
     * 管理员邮箱
     */
    private String adminEmail;

    /**
     * 管理员家住地址
     */
    private String adminAddress;

    /**
     * 管理员密码
     */
    private String adminPwd;

    /**
     * 管理员头像
     */
    private String adminAvatar;

    /**
     * 账户是否可用
     */
    private Boolean isEnable;

    /**
     * 是否是超级管理员
     */
    private Boolean isAdmin;

    /**
     * 所在部门
     */
    private Long deptId;


    /**
     * 重置密码时间
     */
    private LocalDateTime pwdResetTime;


    /**
     * 角色ids
     */
    transient Set<Long> RoleIds;

}
