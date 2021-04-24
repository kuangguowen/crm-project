package com.kgw.controller;

import com.github.pagehelper.PageHelper;
import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.page.PageResult;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.query.MenuCriteria;
import com.kgw.domin.vo.MenuVo;
import com.kgw.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/23 20:35
 */
@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController extends BaseController {


    @Autowired
    private MenuService menuService;


    /**
     * 分页查询  + 前端展示tree
     *
     * @param
     * @return
     */
    @GetMapping
    public AxiosResult<PageResult<MenuVo>> list(MenuCriteria menuCriteria) {

        return AxiosResult.success(menuService.getMenuTree(menuCriteria));
    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(menuService.deleteBy(id));

    }

    /**
     * 批量删除
     */

    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteByBatchId(@PathVariable List<Long> ids) {
        return toAxios(menuService.batchDelete(ids));

    }


    /**
     * 获取所有数据 不进行分页条件查询
     */
    @GetMapping("tree")
    public AxiosResult<List<MenuVo>> getAllMenuTree() {
        List<MenuVo> list = menuService.getAllMenuTree();
        return AxiosResult.success(list);
    }


}
