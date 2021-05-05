package com.kgw.service;

import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.LoginLog;
import com.kgw.domin.query.BrandCriteria;
import com.kgw.domin.query.LoginLogCriteria;
import com.kgw.domin.vo.BrandVo;
import com.kgw.domin.vo.LoginLogVo;
import com.kgw.service.base.BaseService;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:50
 */
public interface LoginLogService extends BaseService<LoginLog> {

    /**
     * 分页条件查询
     * @param
     * @return
     */
    PageResult<LoginLogVo> searchPage(LoginLogCriteria loginLogCriteria);

    LoginLogVo findById(Long id);
}
