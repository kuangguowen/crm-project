package com.kgw.mapper;

import com.kgw.domin.entity.Category;
import com.kgw.mapper.base.MyMapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:44
 */
public interface CategoryMapper extends MyMapper<Category> {

    /**
     * 使用sql查询
     *
     * @param level
     * @return
     */
    @Select("select * from base_category where category_level=#{level}")
    List<Category> findByCategoryLevel(int level);
}
