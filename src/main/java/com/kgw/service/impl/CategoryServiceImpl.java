package com.kgw.service.impl;

import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.Category;
import com.kgw.service.BrandService;
import com.kgw.service.CategoryService;
import com.kgw.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:51
 */
@Service  //service层添加到容器
@Transactional  // 添加事务
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

}
