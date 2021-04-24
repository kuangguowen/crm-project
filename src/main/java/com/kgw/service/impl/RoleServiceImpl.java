package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.AdminRole;
import com.kgw.domin.entity.Role;
import com.kgw.domin.query.RoleCriteria;
import com.kgw.domin.vo.RoleVo;
import com.kgw.mapper.AdminRoleMapper;
import com.kgw.mapper.RoleMapper;
import com.kgw.service.RoleService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.RoleTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:51
 */
@Service  //service层添加到容器
@Transactional  // 添加事务
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private final RoleMapper roleMapper;

    private final RoleTransfer roleTransfer;

    private final AdminRoleMapper adminRoleMapper;

    /**
     * 分页加条件查询
     *
     * @param roleCriteria
     * @return
     */
    @Override
    public PageResult<RoleVo> searchPage(RoleCriteria roleCriteria) {
        // 分页
        PageHelper.startPage(roleCriteria.getCurrentPage(), roleCriteria.getPageSize());
        //拼接条件
        LambdaQueryWrapper<Role> lambda = new QueryWrapper<Role>().lambda();
        if (!StringUtils.isEmpty(roleCriteria.getRoleName())) {
            lambda.like(Role::getRoleName, roleCriteria.getRoleName());
        }
        if (!StringUtils.isEmpty(roleCriteria.getStartTime())) {
            lambda.between(Role::getCreateTime, roleCriteria.getStartTime(), roleCriteria.getEndTime());
        }
        List<Role> roles = this.search(lambda);
        // 获取总条数
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        List<RoleVo> roleVos = roleTransfer.toVO(roles);
        return new PageResult<RoleVo>(pageInfo.getTotal(), roleVos);
    }


    @Override
    public RoleVo findById(Long id) {
        Role byId = getById(id);
        return roleTransfer.toVo(byId);
    }




}
