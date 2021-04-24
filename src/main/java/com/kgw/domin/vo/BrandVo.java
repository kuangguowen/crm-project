package com.kgw.domin.vo;

import com.kgw.domin.vo.base.BaseVo;
import lombok.Data;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 16:42
 */
@Data
public class BrandVo extends BaseVo {

    /**
     * 反回的数据是页面当中有什么就返回什么
     */

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌描述
     */
    private String brandDesc;

    /**
     * 品牌LOGO
     */
    private String brandLogo;

    /**
     * 品牌官网
     */
    private String brandSite;


}
