package com.kgw.controller;

import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Dept;
import com.kgw.http.AxiosResult;
import com.kgw.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:59
 */
@RestController
@RequestMapping("Dept")
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
        return  toAxios(deptService.update(Dept));

    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
       return toAxios(deptService.deleteBy(id));

    }


}
