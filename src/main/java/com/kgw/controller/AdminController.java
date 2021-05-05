package com.kgw.controller;

import com.alibaba.excel.EasyExcel;
import com.kgw.commom.Valid.AddGroup;
import com.kgw.commom.Valid.UpdateGroup;
import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.page.PageResult;
import com.kgw.commom.perm.HasPerm;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Admin;
import com.kgw.domin.entity.excel.AdminExcel;
import com.kgw.domin.query.AdminCriteria;
import com.kgw.domin.vo.AdminVo;
import com.kgw.service.AdminService;
import com.kgw.transfer.AdminExcelTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    private final AdminExcelTransfer adminExcelTransfer;

    /**
     * 分页查询
     *
     * @param adminCriteria
     * @return
     */
    @GetMapping
    public AxiosResult<PageResult<AdminVo>> list(AdminCriteria adminCriteria) {
        PageResult<AdminVo> list = adminService.searchPage(adminCriteria);
        return AxiosResult.success(list);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public AxiosResult<Admin> searchById(@PathVariable Long id) {
        Admin byId = adminService.getAdminAadRoleIdsById(id);
        return AxiosResult.success(byId);
    }

    /**
     * 添加员工 与保存密码
     *
     * @param admin
     * @return
     */
    @HasPerm(perm = "admin:add")
    @PostMapping
    public AxiosResult<Void> add(@Validated(AddGroup.class) @RequestBody Admin admin) {
            admin.setAdminPwd(bCryptPasswordEncoder.encode("123456"));
            admin.setIsAdmin(false);
            return toAxios(adminService.addAdminAndRole(admin));
    }

    /**
     * 修改
     * @param admin
     * @return
     */
    @HasPerm(perm = "admin:edit")
    @PutMapping
    public AxiosResult<Void> update(@Validated(UpdateGroup.class)@RequestBody Admin admin) {
        return toAxios(adminService.updateAdminAanRoles(admin));
    }


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @HasPerm(perm = "admin:delete")
    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(adminService.deleteBy(id));
    }

    /**
     * 批量删除
     */
    @HasPerm(perm = "admin:batch")
    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteByBatchId(@PathVariable List<Long> ids) {
        return toAxios(adminService.batchDelete(ids));
    }


    /**
     * 表格导出
     */
    @HasPerm(perm = "admin:export")
    @GetMapping("export")
    public ResponseEntity<byte[]> export() throws UnsupportedEncodingException {
        List<Admin> list = adminService.list();
        List<AdminExcel> adminExcels = adminExcelTransfer.toVO(list);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        EasyExcel.write(out, AdminExcel.class).sheet("员工信息表").doWrite(adminExcels);
        byte[] bytes = out.toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachement", URLEncoder.encode("员工信息表.xlsx", "utf-8"));
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }


}
