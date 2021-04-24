package com.kgw.domin.query;

import com.kgw.domin.query.base.BaseQueryCriteria;
import lombok.Data;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/20 22:17
 */
@Data
public class AdminCriteria extends BaseQueryCriteria {
    /**
     * 接收参数
     */
    private String adminName;
    private String adminPhone;
    private Long deptId;


}
