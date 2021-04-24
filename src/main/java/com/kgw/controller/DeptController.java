package com.kgw.controller;

import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Dept;
import com.kgw.commom.http.AxiosResult;
import com.kgw.domin.vo.DeptVo;
import com.kgw.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:59
 */
@RestController
@RequestMapping("dept")
@RequiredArgsConstructor
public class DeptController extends BaseController {


    private final DeptService deptService;

    @GetMapping
    public AxiosResult<List<Dept>> list() {
        List<Dept> list = deptService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Dept> searchById(@PathVariable Long id) {
        Dept byId = deptService.getById(id);
        return AxiosResult.success(byId);
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Dept Dept) {
        return toAxios(deptService.add(Dept));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Dept Dept) {
        return toAxios(deptService.update(Dept));

    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(deptService.deleteBy(id));

    }

    /**
     * 通过id找孩子
     **/
    @GetMapping("{id}/children")
    public AxiosResult<List<DeptVo>> getChildrenById(@PathVariable long id) {
        List<DeptVo> childrenById = deptService.getChildrenById(id);
        return AxiosResult.success(childrenById);
    }


    /**
     * 找所有部门数据
     *
     * @return
     */
    @GetMapping("tree")
    public AxiosResult<List<DeptVo>> getAllDeptTree() {
        List<DeptVo> list = deptService.getChildrenById(0L);
        return AxiosResult.success(list);
    }

    /**
     * 找所有部门的,一级数据
     *
     * @return
     */

    @GetMapping("root")
    public AxiosResult<List<DeptVo>> getRootList() {
        List<DeptVo> list = deptService.getAllDeptTree();
        return AxiosResult.success(list);
    }

}
