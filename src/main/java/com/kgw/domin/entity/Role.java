package com.kgw.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/22 19:39
 */
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {
    private String roleName;
    private String roleDesc;


}
