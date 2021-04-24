package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kgw.commom.Transfer.TransferUtils;
import com.kgw.domin.entity.Category;
import com.kgw.domin.vo.CategoryVo;
import com.kgw.mapper.CategoryMapper;
import com.kgw.mapper.base.MyMapper;
import com.kgw.service.CategoryService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.CategoryTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {



    private final CategoryTransfer categoryTransfer;

    private final CategoryMapper categoryMapper;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<CategoryVo> getTree() {
        // 查询所有
        List<Category> list = this.list();
        // 通过VO转换 成 categoryVos
        List<CategoryVo> categoryVos = categoryTransfer.toVO(list);
        // 调用工具类
        return TransferUtils.buildTree(categoryVos);
    }

    /**
     * 封装两层tree
     *
     * @return
     */
    @Override
    public List<CategoryVo> getSelectTree() {
        // 拿到一级分类
        List<Category> first = categoryMapper.findByCategoryLevel(1);
        List<CategoryVo> categoryVos = categoryTransfer.toVO(first);
        // 拿到二级分类
        List<Category> second = categoryMapper.findByCategoryLevel(2);
        List<CategoryVo> secondCategoryVos = categoryTransfer.toVO(second);
        categoryVos.forEach(categoryVo -> {
            List<CategoryVo> collect = secondCategoryVos.stream().filter(item -> item.getParentId().longValue() == categoryVo.getId().longValue()).collect(Collectors.toList());
            categoryVo.setChildren(collect);
        });
        return categoryVos;
    }
}
