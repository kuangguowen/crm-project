package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.Transfer.TransferUtils;
import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Dept;
import com.kgw.domin.query.DeptCriteria;
import com.kgw.domin.vo.DeptVo;
import com.kgw.mapper.DeptMapper;
import com.kgw.service.DeptService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.DeptTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:51
 */
@Service  //service层添加到容器
@Transactional  // 添加事务
@RequiredArgsConstructor
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    private final DeptTransfer deptTransfer;
    private final DeptMapper deptMapper;

    /**
     * 获取所有的部门数据 以tree状结构展示
     *
     * @return
     */
    @Override
    public List<DeptVo> getAllDeptTree() {
        List<Dept> list = this.list();
        List<DeptVo> deptVos = deptTransfer.addChildrenProperties(list);
        List<DeptVo> deptVos1 = TransferUtils.buildTree(deptVos);
        return deptVos1;
    }


    /**
     * 通过部门id找孩子
     *
     * @param id
     * @return
     */
    @Override
    public List<DeptVo> getChildrenById(long id) {
        List<Dept> deptList = deptMapper.selectList(new QueryWrapper<Dept>().lambda().eq(Dept::getParentId, id).orderByAsc(Dept::getDeptSort));
        return deptTransfer.toVO(deptList);
    }

    /**
     * 分页条件查询
     *
     * @param deptCriteria
     * @return
     */
    @Override
    public PageResult<DeptVo> searchPage(DeptCriteria deptCriteria) {

        PageHelper.startPage(deptCriteria.getCurrentPage(), deptCriteria.getPageSize());
        LambdaQueryWrapper<Dept> lambda = new QueryWrapper<Dept>().lambda();
        if (deptCriteria.isQuery()) {
            //        如果是查询 查询的不一定是第一级数据 所以不能拼接一级的条件
            if (!StringUtils.isEmpty(deptCriteria.getDeptName())) {
                lambda.like(Dept::getDeptName, deptCriteria.getDeptName());
            }
            if (!StringUtils.isEmpty(deptCriteria.getStartTime()) && !StringUtils.isEmpty(deptCriteria.getEndTime())) {
                lambda.between(Dept::getCreateTime, deptCriteria.getStartTime(), deptCriteria.getEndTime());
            }
        } else {
            //如果不是条件查询 则查询的是第一级
            lambda.eq(Dept::getParentId, 0).orderByAsc(Dept::getDeptSort);
        }
        List<Dept> depts = deptMapper.selectList(lambda);
        PageInfo<Dept> pageInfo = new PageInfo<>(depts);
        return new PageResult<DeptVo>(pageInfo.getTotal(), deptTransfer.toVO(depts));
    }
}
