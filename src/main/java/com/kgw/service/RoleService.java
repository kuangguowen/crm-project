package com.kgw.service;

import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.Role;
import com.kgw.domin.query.BrandCriteria;
import com.kgw.domin.query.RoleCriteria;
import com.kgw.domin.vo.BrandVo;
import com.kgw.domin.vo.RoleVo;
import com.kgw.service.base.BaseService;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:50
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 分页条件查询
     * @param
     * @return
     */
    PageResult<RoleVo> searchPage(RoleCriteria roleCriteria);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    RoleVo findById(Long id);

    /**
     * 给角色赋予权限
     */
    int getRoleByMenu(Long roleId, List<Long> menuIds);

    /**
     * 获取角色权限（不全 只用于前端展示）
     */
    List<Long> getRoleTreeByMenu(Long roleId);


}
