package com.kgw.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kgw.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class Menu extends BaseEntity {

  private String menuTitle;
  private Long parentId;
  private int menuType;
  private int menuSort;
  private String menuPath;
  private String menuIcon;
  private String component;
  private boolean isHidden;
  private String permission;


}
