package com.kgw.domin.query;

import com.kgw.domin.query.base.BaseQueryCriteria;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 15:29
 */
@Data
public class DeptCriteria extends BaseQueryCriteria {

    private String deptName;
    /**
     * 判断是否是查询
     * @return
     */
    public boolean isQuery() {
        return !StringUtils.isEmpty(this.deptName) || (!StringUtils.isEmpty(this.getStartTime()) && !StringUtils.isEmpty(this.getEndTime()));
    }
}
