package com.kgw.domin.vo;

import com.kgw.domin.vo.base.BaseVo;
import lombok.Data;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/18 17:52
 */
@Data
public class CategoryVo extends BaseVo {

    /**
     * 分类名称
     */
    private String catetoryName;

    /**
     * 分类父id 如果是顶级分类 父id 为0
     */
    private Long parentId;

    /**
     * 分类等级 一共三级分类 1 2 3
     */
    private Integer categoryLevel;


    /**
     * 添加一个与前端数据传输对应的属性 children
     */
    List<CategoryVo> children;
}
