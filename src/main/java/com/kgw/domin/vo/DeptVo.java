package com.kgw.domin.vo;

import com.kgw.domin.vo.base.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 16:42
 */
@Data
public class DeptVo extends BaseVo {

    /**
     * 反回的数据是页面当中有什么就返回什么
     */

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门的上级部门id 如果是一级部门 则父id为0
     */
    private Long parentId;

    /**
     * 部门排序
     */
    private Integer deptSort;

    /**
     * 部门描述
     */
    private String deptDesc;

    /**
     * 新增属性 children
     */
     List<DeptVo> children;


}
