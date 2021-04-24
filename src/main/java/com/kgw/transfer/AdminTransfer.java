package com.kgw.transfer;

import com.kgw.domin.entity.Admin;
import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.Dept;
import com.kgw.domin.vo.AdminVo;
import com.kgw.domin.vo.BrandVo;
import com.kgw.mapper.DeptMapper;
import com.kgw.transfer.base.BaseTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 17:12
 */
@Component
public class AdminTransfer extends BaseTransfer<Admin, AdminVo> {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 转换性别
     */
    public List<AdminVo> setSex(List<Admin> list) {
        List<AdminVo> adminVos = super.toVO(list);
        for (int i = 0; i < adminVos.size(); i++) {
            Admin admin = list.get(i);
            AdminVo adminVo = adminVos.get(i);
            Dept dept = deptMapper.selectById(admin.getDeptId());

            adminVo.setDeptName(dept == null ? "" : dept.getDeptName());

            if (admin.getGender() == 0) {
                adminVo.setSex("男");
            } else if (admin.getGender() == 1) {
                adminVo.setSex("女");
            } else {
                adminVo.setSex("未知");
            }
        }
        return adminVos;

    }

}
