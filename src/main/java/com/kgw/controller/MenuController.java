package com.kgw.controller;

import com.github.pagehelper.PageHelper;
import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.page.PageResult;
import com.kgw.commom.perm.HasPerm;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Menu;
import com.kgw.domin.query.MenuCriteria;
import com.kgw.domin.vo.MenuVo;
import com.kgw.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    /**
     *
     * 获得id
     */
    @GetMapping("{id}")
    public AxiosResult<Menu> findById(@PathVariable Long id) {
        return AxiosResult.success(menuService.getById(id));
    }


    /**
     * 删除
     */
    @HasPerm(perm = "menu:delete")
    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(menuService.deleteBy(id));
    }

    /**
     * 批量删除
     */
    @HasPerm(perm = "menu:batch")
    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteByBatchId(@PathVariable List<Long> ids) {
        return toAxios(menuService.batchDelete(ids));

    }


    /**
     * 获取所有数据 不进行分页条件查询
     */
    @GetMapping("tree")
    public AxiosResult<List<MenuVo>> getAllMenuTree() {
        ArrayList<MenuVo> arrayList = new ArrayList<>();
        List<MenuVo> list = menuService.getAllMenuTree();
        MenuVo menuVo = new MenuVo();
        menuVo.setId(0L);
        menuVo.setMenuTitle("主目录");
        menuVo.setChildren(list);
        arrayList.add(menuVo);
        return AxiosResult.success(arrayList);
    }

    /**
     * 添加
     */
    @HasPerm(perm = "menu:add")
    @PostMapping
    public AxiosResult<Void> add(@RequestBody Menu menu) {
        return toAxios(menuService.add(menu));
    }


    /**
     * 修改
     */
    @HasPerm(perm = "menu:edit")
    @PutMapping
    public AxiosResult<Void> update(@RequestBody Menu menu) {
        return toAxios(menuService.update(menu));
    }




}
