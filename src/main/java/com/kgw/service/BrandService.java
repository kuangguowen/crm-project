package com.kgw.service;

import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Admin;
import com.kgw.domin.entity.Brand;
import com.kgw.domin.query.BrandCriteria;
import com.kgw.domin.vo.BrandVo;
import com.kgw.service.base.BaseService;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:50
 */
public interface BrandService extends BaseService<Brand> {

    /**
     * 分页条件查询
     * @param brandCriteria
     * @return
     */
    PageResult<BrandVo> searchPage(BrandCriteria brandCriteria);

    BrandVo findById(Long id);
}
