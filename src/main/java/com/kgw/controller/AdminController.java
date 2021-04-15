package com.kgw.controller;

import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Admin;
import com.kgw.http.AxiosResult;
import com.kgw.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:59
 */
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public AxiosResult<List<Admin>> list() {
        List<Admin> list = adminService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Admin> searchById(@PathVariable Long id) {
        Admin byId = adminService.getById(id);
        return AxiosResult.success(byId);
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Admin admin) {
        return toAxios(adminService.add(admin));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Admin admin) {
        return  toAxios(adminService.update(admin));

    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
       return toAxios(adminService.deleteBy(id));

    }


}
