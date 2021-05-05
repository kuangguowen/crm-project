package com.kgw.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kgw.commom.Valid.AddGroup;
import com.kgw.commom.Valid.SexList;
import com.kgw.commom.Valid.UpdateGroup;
import com.kgw.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$", message = "请输入正确的用户名,只含有汉字、数字、字母、下划线不能以下划线开头和结尾", groups = {AddGroup.class, UpdateGroup.class})
    private String adminName;

    /**
     * 管理员昵称
     */
    @NotBlank(message = "用户昵称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String nickName;

    /**
     * 管理员性别 0 : 男   1：女     2： 表示未知
     */
    @SexList(sex = {0, 1, 2}, message = "必须输入0,1,2", groups = {AddGroup.class, UpdateGroup.class})
    private Integer gender;

    /**
     * 管理员手机
     */
    @Pattern(regexp = "/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/"
            , message = "请输入正确的手机号123")
    @NotBlank(message = "手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminPhone;

    /**
     * 管理员邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "输入的邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String adminEmail;

    /**
     * 管理员家住地址
     */
    @NotBlank(message = "住址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAddress;

    /**
     * 管理员密码
     */
    private String adminPwd;

    /**
     * 管理员头像
     */
    @URL(message = "图片格式不正确", groups = {AddGroup.class, UpdateGroup.class})
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
