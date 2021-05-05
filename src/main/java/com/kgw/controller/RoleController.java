package com.kgw.controller;

import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.page.PageResult;
import com.kgw.commom.perm.HasPerm;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Role;
import com.kgw.domin.query.RoleCriteria;
import com.kgw.domin.vo.RoleVo;
import com.kgw.service.RoleService;
import com.kgw.transfer.RoleTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:59
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleTransfer roleTransfer;


    /**
     * 分页条件查询
     *
     * @param roleCriteria
     * @return
     */
    @GetMapping
    public AxiosResult<PageResult<RoleVo>> list(RoleCriteria roleCriteria) {
        PageResult<RoleVo> pageResult = roleService.searchPage(roleCriteria);
        return AxiosResult.success(pageResult);
    }


    /**
     * 查询所有
     */
    @GetMapping("findAll")
    public AxiosResult<List<RoleVo>> findAll() {
        List<Role> list = roleService.list();
        List<RoleVo> roleVos = roleTransfer.toVO(list);
        return AxiosResult.success(roleVos);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public AxiosResult<RoleVo> searchById(@PathVariable Long id) {
        RoleVo RoleVo = roleService.findById(id);
        return AxiosResult.success(RoleVo);
    }


    /**
     * 添加
     *
     * @param role
     * @return
     */
    @HasPerm(perm = "role:add")
    @PostMapping
    public AxiosResult<Void> add(@RequestBody Role role) {

        return toAxios(roleService.add(role));

    }


    /**
     * 修改
     *
     * @param role
     * @return
     */
    @HasPerm(perm = "role:edit")
    @PutMapping
    public AxiosResult<Void> update(@RequestBody Role role) {

        return toAxios(roleService.update(role));

    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @HasPerm(perm = "role:delete")
    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(roleService.deleteBy(id));

    }


    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @HasPerm(perm = "role:batch")
    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> batchDelete(@PathVariable List<Long> ids) {
        return toAxios(roleService.batchDelete(ids));
    }


    /**
     * 给角色赋予权限
     */
    @PutMapping("{roleId}/menu")
    public AxiosResult<Void> getRoleByMenu(@PathVariable Long roleId, @RequestBody List<Long> menuIds) {
        int row = roleService.getRoleByMenu(roleId, menuIds);
        return toAxios(row);
    }


    /**
     * 获取角色权限（不全 只用于前端展示）
     */
    @GetMapping("{roleId}/treeMenu")
    public AxiosResult<List<Long>> getRoleTreeByMenu(@PathVariable Long roleId) {
        List<Long> ids = roleService.getRoleTreeByMenu(roleId);
        return AxiosResult.success(ids);
    }


}
