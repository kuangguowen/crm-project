package com.kgw.mapper;

import com.kgw.domin.entity.AdminRole;
import com.kgw.mapper.base.MyMapper;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:44
 */
public interface AdminRoleMapper extends MyMapper<AdminRole> {

//    @Insert("insert into sys_admin_role values(#{adminId},#{roleId})")
//    public void insert(@Param("adminId") long adminId, @Param("roleId") long roleId);
}
