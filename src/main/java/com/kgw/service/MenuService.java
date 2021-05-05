package com.kgw.service;

import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Menu;
import com.kgw.domin.entity.Role;
import com.kgw.domin.query.MenuCriteria;
import com.kgw.domin.vo.MenuVo;
import com.kgw.service.base.BaseService;

import java.util.List;
import java.util.Set;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:50
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 获取所有的菜单
     * @return
     */
    PageResult<MenuVo> getMenuTree(MenuCriteria menuCriteria);

    List<MenuVo> getAllMenuTree();

    /**
     * 通过用户名id 找到所有的权限
     */
    List<Menu> getMenusByAdminId(Long adminId);
}
