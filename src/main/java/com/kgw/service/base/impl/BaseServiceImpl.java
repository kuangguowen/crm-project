package com.kgw.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.kgw.commom.reflect.ReflectionUtils;
import com.kgw.mapper.base.MyMapper;
import com.kgw.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/14 22:16
 */

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private MyMapper<T> myMapper;


    /**
     * 返回一个MyMapper
     * @return
     */
    protected MyMapper<T> getMyMapper(){
        return myMapper;
    }

    @Override
    public List<T> list() {
        return myMapper.selectList(null);
    }


    @Override
    public T getById(Long id) {
        return myMapper.selectById(id);
    }


    @Override
    public List<T> search(Wrapper<T> wrapper) {
        return myMapper.selectList(wrapper);
    }

    @Override
    public int add(T t) {
        ReflectionUtils.invokeMethod(t, "setData", null, null);
        return myMapper.insert(t);
    }

    @Override
    public int update(T t) {
        ReflectionUtils.invokeMethod(t, "setData", null, null);
        return myMapper.updateById(t);
    }

    @Override
    public int deleteBy(Long id) {
        return myMapper.deleteById(id);
    }


    @Transactional
    @Override
    public int batchDelete(List<Long> ids) {
        return myMapper.deleteBatchIds(ids);
    }
}
