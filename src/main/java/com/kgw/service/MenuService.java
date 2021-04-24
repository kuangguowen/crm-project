package com.kgw.service;

import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Menu;
import com.kgw.domin.query.MenuCriteria;
import com.kgw.domin.vo.MenuVo;
import com.kgw.service.base.BaseService;

import java.util.List;

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
}
