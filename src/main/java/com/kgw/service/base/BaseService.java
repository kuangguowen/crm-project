package com.kgw.service.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/14 22:01
 */
public interface BaseService<T>  {
    /**
     * 查询所有
     */
    List<T> list();

    /**
     * 根据id查询
     */
    T getById(Long id);

    /**
     * 条件查询
     */
    List<T> search(Wrapper<T> wrapper);


    /**
     * 添加
     */
    int add(T t);


    /**
     * 修改
     */
    int update(T t);


    /**
     * 通过id删除
     */
    int deleteBy(Long id);


    /**
     * 批量删除
     */
    int batchDelete(List<Long> ids);

}
