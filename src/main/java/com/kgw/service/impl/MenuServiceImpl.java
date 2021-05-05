package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.Transfer.TransferUtils;
import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.AdminRole;
import com.kgw.domin.entity.Menu;
import com.kgw.domin.entity.Role;
import com.kgw.domin.entity.RoleMenu;
import com.kgw.domin.query.MenuCriteria;
import com.kgw.domin.vo.MenuVo;
import com.kgw.mapper.AdminRoleMapper;
import com.kgw.mapper.MenuMapper;
import com.kgw.mapper.RoleMapper;
import com.kgw.mapper.RoleMenuMapper;
import com.kgw.service.MenuService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.MenuTransfer;
import lombok.RequiredArgsConstructor;
import org.jsoup.select.Collector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    private final MenuTransfer menuTransfer;
    private final AdminRoleMapper adminRoleMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final MenuMapper menuMapper;

    /**
     * 获取所有的菜单 通过树形展示
     *
     * @return
     */
    @Override
    public PageResult<MenuVo> getMenuTree(MenuCriteria menuCriteria) {
        // 分页条件查询
        PageHelper.startPage(menuCriteria.getCurrentPage(),menuCriteria.getPageSize());
        //获取到一级数据进行分页
        List<Menu> root = this.search(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0));
        // 获取分页数据 （总条数）
        PageInfo<Menu> pageInfo = new PageInfo<>(root);
        // 转换vo
        List<MenuVo> rootVo = menuTransfer.toVO(root);
        // 排序
        Collections.sort(rootVo,(t,t1)->t.getMenuSort()-t1.getMenuSort());
        // 通过一级找二级
        List<Menu> other = this.search(new QueryWrapper<Menu>().lambda().ne(Menu::getParentId, 0));
        List<MenuVo> otherVo = menuTransfer.toVO(other);
        rootVo.forEach(item->{
            getChildren(item,otherVo);
        });

        return new PageResult<MenuVo>(pageInfo.getTotal(),rootVo);
    }



    /**
     * 找孩子
     */
    public void getChildren(MenuVo menuVo, List<MenuVo> list) {
        // 找到孩子 并且排序
        List<MenuVo> second = list.stream().filter(item -> item.getParentId().longValue() == menuVo.getId().longValue()).sorted((t, t1) -> t.getMenuSort() - t1.getMenuSort()).collect(Collectors.toList());
        // 判断是否有孩子 孩子是否为空
        if (second.size() > 0 && second != null) {
            // 不为空 不是 null 进行set
            menuVo.setChildren(second);
            // 添加完把第二级孩子删除
            list.removeAll(second);
            // 递归寻找
            second.forEach(item->{
                getChildren(item,list);
            });
        }
    }


    /**
     * 获取所有的菜单
     */
    @Override
    public List<MenuVo> getAllMenuTree() {
        List<Menu> list = this.list();
        // 获取到一级菜单
        List<Menu> root = list.stream().filter(item -> item.getParentId().longValue() == 0).sorted((t, t1) -> t.getMenuSort() - t1.getMenuSort()).collect(Collectors.toList());
        list.removeAll(root);
        // 转vo
        List<MenuVo> menuVos = menuTransfer.toVO(list);
        List<MenuVo> rootVo = menuTransfer.toVO(root);
        // 找孩子
        rootVo.forEach(item->{
            getChildren(item,menuVos);
        });
        return rootVo;
    }


    /**
     * 通过用户名id 找到所有的role
     * @param adminId
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId(Long adminId) {
        List<Long> RoleIds = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, adminId)).stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        Set<Long> menuIds = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getRoleId, RoleIds)).stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        List<Menu> menus = menuMapper.selectBatchIds(menuIds);
        return menus;
    }


}
