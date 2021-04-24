package com.kgw.service;


import com.kgw.domin.entity.Category;
import com.kgw.domin.vo.CategoryVo;
import com.kgw.service.base.BaseService;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:50
 */
public interface CategoryService extends BaseService<Category> {

    /**
     * 查询所有获取树形表格的方法
     * @return
     */
    List<CategoryVo> getTree();

    List<CategoryVo> getSelectTree();
}
