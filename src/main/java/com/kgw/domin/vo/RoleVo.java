package com.kgw.domin.vo;

import com.kgw.domin.vo.base.BaseVo;
import lombok.Data;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 16:42
 */
@Data
public class RoleVo extends BaseVo {

    /**
     * 反回的数据是页面当中有什么就返回什么
     */

    private String roleName;

    private String roleDesc;

}
