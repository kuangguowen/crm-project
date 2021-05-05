package com.kgw.service;

import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Admin;
import com.kgw.domin.query.AdminCriteria;
import com.kgw.domin.vo.AdminVo;
import com.kgw.service.base.BaseService;

import java.util.Map;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:50
 */
public interface AdminService extends BaseService<Admin> {
    /**
     * 分页条件查询
     * @param adminCriteria
     * @return
     */
    PageResult<AdminVo> searchPage(AdminCriteria adminCriteria);

    /**
     * 保存员工和员工角色
     * @param admin
     * @return
     */
    int addAdminAndRole(Admin admin);

    /**
     *
     * @param id
     * @return
     */
    Admin getAdminAadRoleIdsById(Long id);


    int updateAdminAanRoles(Admin admin);

    /**
     * 通过用户名找用户
     */
    Admin getUserByAdmin(String username);

    /**
     * 获得用户信息
     * @return
     */
    Map<String, Object> getAdminInfo();
}
