package com.kgw.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/25 16:58
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu {

    private Long roleId;
    private Long menuId;
}
