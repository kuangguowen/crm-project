package com.kgw.domin.vo;

import com.kgw.domin.vo.base.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/23 20:45
 */
@Data
public class MenuVo extends BaseVo {


    private String menuTitle;
    private Long parentId;
    private int menuType;
    private int menuSort;
    private String menuPath;
    private String menuIcon;
    private String component;
    private boolean isHidden;
    private String permission;
    List<MenuVo> children;

}
