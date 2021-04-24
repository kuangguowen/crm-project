package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Admin;
import com.kgw.domin.entity.AdminRole;
import com.kgw.domin.query.AdminCriteria;
import com.kgw.domin.vo.AdminVo;
import com.kgw.domin.vo.BrandVo;
import com.kgw.mapper.AdminMapper;
import com.kgw.mapper.AdminRoleMapper;
import com.kgw.service.AdminService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.AdminTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:51
 */
@Service  //service层添加到容器
@Transactional  // 添加事务
@RequiredArgsConstructor
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    private final AdminMapper adminMapper;
    private final AdminTransfer adminTransfer;
    private final AdminRoleMapper adminRoleMapper;

    /**
     * 分页条件查询
     *
     * @param adminCriteria
     * @return
     */
    @Override
    public PageResult<AdminVo> searchPage(AdminCriteria adminCriteria) {
        // 先获取分页
        PageHelper.startPage(adminCriteria.getCurrentPage(), adminCriteria.getPageSize());
        LambdaQueryWrapper<Admin> lambda = new QueryWrapper<Admin>().lambda();

        if (!StringUtils.isEmpty(adminCriteria.getAdminName())) {
            lambda.like(Admin::getAdminName, adminCriteria.getAdminName());
        }
        if (!StringUtils.isEmpty(adminCriteria.getAdminPhone())) {
            lambda.eq(Admin::getAdminPhone, adminCriteria.getAdminPhone());
        }
        if (!StringUtils.isEmpty(adminCriteria.getDeptId())) {
            lambda.eq(Admin::getDeptId, adminCriteria.getDeptId());
        }
        if (adminCriteria.getStartTime() != null) {
            lambda.between(Admin::getCreateTime, adminCriteria.getStartTime(), adminCriteria.getEndTime());
        }

        List<Admin> admins = adminMapper.selectList(lambda);
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);

        List<AdminVo> adminVos = adminTransfer.setSex(admins);
        return new PageResult<AdminVo>(pageInfo.getTotal(), adminVos);
    }


    /**
     * 保存员工和员工角色
     *
     * @param admin
     * @return
     */
    @Override
    public int addAdminAndRole(Admin admin) {
        int add = this.add(admin);
        Set<Long> roleIds = admin.getRoleIds();
        roleIds.forEach(roleId -> {
            adminRoleMapper.insert(new AdminRole(admin.getId(), roleId));
        });
        return add;
    }


    /**添加用户和用户的角色
     * @param id
     * @return
     */
    @Override
    public Admin getAdminAadRoleIdsById(Long id) {
        // 获取到adminId
        Admin byId = this.getById(id);
        System.out.println(byId);
        // 查询roleId
        List<AdminRole> adminRoles = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, id));
        byId.setRoleIds(adminRoles.stream().map(AdminRole::getRoleId).collect(Collectors.toSet()));
        return byId;
    }


    /**
     * 更新用户和用户的角色
     * @param admin
     * @return
     */
    @Override
    public int updateAdminAanRoles(Admin admin) {
        // 删除
        adminRoleMapper.delete(new UpdateWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId,admin.getId()));
        admin.getRoleIds().forEach(roleId->{
            adminRoleMapper.insert(new AdminRole(admin.getId(),roleId));
        });

        return this.update(admin);
    }
}
