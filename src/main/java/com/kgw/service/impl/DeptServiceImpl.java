package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kgw.commom.Transfer.TransferUtils;
import com.kgw.domin.entity.Dept;
import com.kgw.domin.vo.DeptVo;
import com.kgw.mapper.DeptMapper;
import com.kgw.service.DeptService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.DeptTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

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
}
