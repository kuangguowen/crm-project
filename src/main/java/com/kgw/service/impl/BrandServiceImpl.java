package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.Dept;
import com.kgw.domin.query.BrandCriteria;
import com.kgw.domin.vo.BrandVo;
import com.kgw.mapper.BrandMapper;
import com.kgw.service.BrandService;
import com.kgw.service.DeptService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.BrandTransfer;
import com.kgw.transfer.base.BaseTransfer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:51
 */
@Service  //service层添加到容器
@Transactional  // 添加事务
public class BrandServiceImpl extends BaseServiceImpl<Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private BrandTransfer brandTransfer;

    /**
     * 分页条件查询
     */
    @Override
    public PageResult<BrandVo> searchPage(BrandCriteria brandCriteria) {
        // 开启分页
        PageHelper.startPage(brandCriteria.getCurrentPage(), brandCriteria.getPageSize());
        // 查询
        LambdaQueryWrapper<Brand> lambda = new QueryWrapper<Brand>().lambda();
        if (!StringUtils.isEmpty(brandCriteria.getBrandName())) {
            // 如果品牌名不为空 拼接条件
            lambda.like(Brand::getBrandName, brandCriteria.getBrandName());
        }
        // 拿到时间 进行比较
        LocalDateTime startTime = brandCriteria.getStartTime();
        LocalDateTime endTime = brandCriteria.getEndTime();
        if (startTime != null && endTime != null) {
            // 判断时间 在什么 与 什么之间
            lambda.between(Brand::getCreateTime, startTime, endTime);
        }
        List<Brand> brands = brandMapper.selectList(lambda);

        // 获取总条数
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        System.out.println(pageInfo.getTotal());
        return new PageResult<BrandVo>(pageInfo.getTotal(), brandTransfer.toVO(brands));
    }

    /**
     * VO 专门用来与前端交互的方法
     * @param id
     * @return
     */

    /**
     *通过id进行查询
     */
    @Override
    public BrandVo findById(Long id) {
        Brand byId = getById(id);
       return brandTransfer.toVo(byId);
    }
}
