package com.kgw.controller;

import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Good;
import com.kgw.http.AxiosResult;
import com.kgw.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:59
 */
@RestController
@RequestMapping("Good")
@RequiredArgsConstructor
public class GoodController extends BaseController {

    @Autowired
    private GoodService goodService;

    @GetMapping
    public AxiosResult<List<Good>> list() {
        List<Good> list = goodService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Good> searchById(@PathVariable Long id) {
        Good byId = goodService.getById(id);
        return AxiosResult.success(byId);
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Good Good) {
        return toAxios(goodService.add(Good));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Good Good) {
        return  toAxios(goodService.update(Good));

    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
       return toAxios(goodService.deleteBy(id));

    }


}
