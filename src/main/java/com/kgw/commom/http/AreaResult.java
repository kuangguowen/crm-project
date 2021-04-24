package com.kgw.commom.http;

import lombok.Data;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/21 11:31
 */
@Data
public class AreaResult {

    private String reason;
    private List<AreaItem> result;
}
