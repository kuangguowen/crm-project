package com.kgw.transfer;

import com.kgw.domin.entity.Admin;
import com.kgw.domin.entity.Dept;
import com.kgw.domin.entity.excel.AdminExcel;
import com.kgw.domin.vo.AdminVo;
import com.kgw.mapper.DeptMapper;
import com.kgw.transfer.base.BaseTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 17:12
 */
@Component
@RequiredArgsConstructor
public class AdminExcelTransfer extends BaseTransfer<Admin, AdminExcel> {

    private final DeptMapper deptMapper;


    /**
     * Excel表格的转换
     * @param list
     * @return
     */
    @Override
    public List<AdminExcel> toVO(List<Admin> list) {
        List<AdminExcel> adminExcel = super.toVO(list);
        for (int i = 0; i < list.size(); i++) {
            Admin admin = list.get(i);
            AdminExcel excel = adminExcel.get(i);
            if (admin.getGender() == 0) {
                excel.setSex("男");
            }
            if (admin.getGender() == 1) {
                excel.setSex("女");
            }
            if (admin.getGender() == 2) {
                excel.setSex("未知");
            }
            try {
                excel.setUrl(new URL(admin.getAdminAvatar()));
            } catch (MalformedURLException e) {
            }
            excel.setDeptName(deptMapper.selectById(admin.getDeptId()).getDeptName());
            excel.setIsActive(admin.getIsEnable() ? "可用" : "不可用");
        }
        return adminExcel;

    }
}
