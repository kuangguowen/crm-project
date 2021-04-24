package com.kgw.service;

import com.kgw.domin.entity.Category;
import com.kgw.domin.entity.Dept;
import com.kgw.domin.vo.DeptVo;
import com.kgw.service.base.BaseService;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:50
 */
public interface DeptService extends BaseService<Dept> {


    /**
     * 找所有的部门
     * @return
     */
    List<DeptVo> getAllDeptTree();

    /**
     * 通过id找孩子
     * @param id
     * @return
     */
    List<DeptVo> getChildrenById(long id);
}
