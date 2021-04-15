package com.kgw.controller.base;

import com.kgw.http.AxiosResult;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:58
 */
public class BaseController {

    protected AxiosResult<Void> toAxios(int row) {
        return row > 0 ? AxiosResult.success() : AxiosResult.error();
    }

}
