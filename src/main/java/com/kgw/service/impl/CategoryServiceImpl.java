package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.Transfer.TransferUtils;
import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.Category;
import com.kgw.domin.query.CategoryCriteria;
import com.kgw.domin.vo.CategoryVo;
import com.kgw.domin.vo.MenuVo;
import com.kgw.mapper.CategoryMapper;
import com.kgw.mapper.base.MyMapper;
import com.kgw.service.CategoryService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.CategoryTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public PageResult<CategoryVo> getTree(CategoryCriteria categoryCriteria) {
        //开启分页
        PageHelper.startPage(categoryCriteria.getCurrentPage(),categoryCriteria.getPageSize());
        //获取到一级数据分页
        List<Category> root = categoryMapper.selectList(new QueryWrapper<Category>().lambda().eq(Category::getParentId, 0));
        // 获取分页数据 （总条数）
        PageInfo<Category> pageInfo = new PageInfo<>(root);
        //转换成vo
        List<CategoryVo> categoryVos1 = categoryTransfer.toVO(root);
        // 查询父id不为0 的
        List<Category> search = this.search(new QueryWrapper<Category>().lambda().ne(Category::getParentId, 0));
        List<CategoryVo> categoryVos = categoryTransfer.toVO(search);
        categoryVos1.forEach(categoryVo -> {
            getChildren(categoryVo,categoryVos);
        });
        // 调用工具类
        return new PageResult<CategoryVo>(pageInfo.getTotal(),categoryVos1);
    }

    /**
     * 找孩子
     */
    public void getChildren(CategoryVo categoryVo, List<CategoryVo> list) {
        // 找到孩子 并且排序
        List<CategoryVo> second = list.stream().filter(item -> item.getParentId().longValue() == categoryVo.getId().longValue()).collect(Collectors.toList());
        // 判断是否有孩子 孩子是否为空
        if (second.size() > 0 && second != null) {
            // 不为空 不是 null 进行set
            categoryVo.setChildren(second);
            // 添加完把第二级孩子删除
            list.removeAll(second);
            // 递归寻找
            second.forEach(item->{
                getChildren(item,list);
            });
        }
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
