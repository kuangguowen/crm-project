package com.kgw.domin.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/29 19:30
 */
@Data
public class LoginAdmin {

    private String uuid;

    private Admin admin;

    private List<Menu> perms;

}
