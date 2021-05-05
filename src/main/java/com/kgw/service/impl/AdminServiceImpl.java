package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.Transfer.TransferUtils;
import com.kgw.commom.page.PageResult;
import com.kgw.commom.utils.TokenServiceUtils;
import com.kgw.domin.entity.Admin;
import com.kgw.domin.entity.AdminRole;
import com.kgw.domin.entity.LoginAdmin;
import com.kgw.domin.entity.Menu;
import com.kgw.domin.query.AdminCriteria;
import com.kgw.domin.vo.AdminVo;
import com.kgw.domin.vo.BrandVo;
import com.kgw.domin.vo.MenuVo;
import com.kgw.mapper.AdminMapper;
import com.kgw.mapper.AdminRoleMapper;
import com.kgw.mapper.MenuMapper;
import com.kgw.service.AdminService;
import com.kgw.service.MenuService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.AdminTransfer;
import com.kgw.transfer.MenuTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final TokenServiceUtils tokenServiceUtils;
    private final MenuMapper menuMapper;
    private final MenuTransfer menuTransfer;
    private final MenuService menuService;

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
        if (roleIds != null) {
            roleIds.forEach(roleId -> {
                adminRoleMapper.insert(new AdminRole(admin.getId(), roleId));
            });
        }
        return add;
    }


    /**
     * 添加用户和用户的角色
     *
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
     *
     * @param admin
     * @return
     */
    @Override
    public int updateAdminAanRoles(Admin admin) {
        // 删除
        adminRoleMapper.delete(new UpdateWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, admin.getId()));
        admin.getRoleIds().forEach(roleId -> {
            adminRoleMapper.insert(new AdminRole(admin.getId(), roleId));
        });
        return this.update(admin);
    }

    /**
     * 通过用户名查用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getUserByAdmin(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .lambda().eq(Admin::getAdminName, username));
    }


    /**
     * 获取用户信息
     * 包括本身信息 按钮级别权限
     * @return
     */
    @Override
    public Map<String, Object> getAdminInfo() {
        //拿到用户信息
        Admin admin = tokenServiceUtils.getLoginAdmin().getAdmin();
        Map<String,Object> map = new HashMap<>();
        map.put("admin",admin);
        List<Menu> list = null;
        //拿到用户菜单
        if (tokenServiceUtils.isAdmin()){
            //是超级管理员
            list = menuMapper.selectList(null);
            System.out.println("所有权限："+list);
        }else {
            //不是超级管理员
            list = menuService.getMenusByAdminId(tokenServiceUtils.getAdminId());
        }
        LoginAdmin loginAdmin = tokenServiceUtils.getLoginAdmin();
        loginAdmin.setPerms(list);
        tokenServiceUtils.setLoginAdmin(loginAdmin);

        //过滤按钮级别权限 (拿到菜单级别权限)
        List<Menu> collect = list.stream().filter(menu -> menu.getMenuType() != 2).collect(Collectors.toList());
        System.out.println("菜单列表："+collect);
        List<MenuVo> menuVos = menuTransfer.toVO(collect);
        System.out.println("vo列表："+menuVos);
        List<MenuVo> menuVos1 = TransferUtils.buildTree(menuVos);
        System.out.println("tree："+menuVos1);
        map.put("menuList",menuVos1);
        // 获取按钮级别权限
        List<Menu> btnPerm = list.stream().filter(menu -> menu.getMenuType() != 0).collect(Collectors.toList());
        map.put("btnPerm",btnPerm);
        return map;
    }
}
