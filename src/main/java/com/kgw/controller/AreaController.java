package com.kgw.controller;

import com.kgw.commom.http.AreaItem;
import com.kgw.commom.http.AreaResult;
import com.kgw.commom.http.AxiosResult;
import com.kgw.controller.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/21 11:20
 */
@RequestMapping("area")
@RequiredArgsConstructor
@RestController
public class AreaController extends BaseController {


    private final RestTemplate restTemplate;

    /**
     * 省市区联动数据
     * @param id
     * @return
     */
    @GetMapping("{id}/children")
    public AxiosResult<List<AreaItem>> getChildren(@PathVariable String id) {

        String path = "http://apis.juhe.cn/xzqh/query?key=b0f65d6bd4b0ff717363861555f8b01c&fid=" + id;
        AreaResult areaResult = restTemplate.getForObject(path, AreaResult.class);

        return AxiosResult.success(areaResult.getResult());


    }


}
