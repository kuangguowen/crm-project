package com.kgw.controller;

import com.kgw.commom.page.PageResult;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Admin;
import com.kgw.commom.http.AxiosResult;
import com.kgw.domin.query.AdminCriteria;
import com.kgw.domin.vo.AdminVo;
import com.kgw.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 分页查询
     * @param adminCriteria
     * @return
     */
    @GetMapping
    public AxiosResult<PageResult<AdminVo>> list(AdminCriteria adminCriteria) {
        PageResult<AdminVo> list = adminService.searchPage(adminCriteria);
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Admin> searchById(@PathVariable Long id) {
        Admin byId = adminService.getAdminAadRoleIdsById(id);
        return AxiosResult.success(byId);
    }

    /**
     * 添加员工 与保存密码
     * @param admin
     * @return
     */
    @PostMapping
    public AxiosResult<Void> add(@RequestBody Admin admin) {
        admin.setAdminPwd(bCryptPasswordEncoder.encode("123456"));
        admin.setIsAdmin(false);
        return toAxios(adminService.addAdminAndRole(admin));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Admin admin) {
        return  toAxios(adminService.updateAdminAanRoles(admin));

    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
       return toAxios(adminService.deleteBy(id));

    }

    /**
     * 批量删除
     */

    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteByBatchId(@PathVariable List<Long> ids){
        return toAxios(adminService.batchDelete(ids));

    }


}
