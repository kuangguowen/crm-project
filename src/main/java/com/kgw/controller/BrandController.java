package com.kgw.controller;

import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Brand;
import com.kgw.http.AxiosResult;
import com.kgw.service.BrandService;
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
@RequestMapping("Brand")
@RequiredArgsConstructor
public class BrandController extends BaseController {


    private final BrandService brandService;

    @GetMapping
    public AxiosResult<List<Brand>> list() {
        List<Brand> list = brandService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Brand> searchById(@PathVariable Long id) {
        Brand byId = brandService.getById(id);
        return AxiosResult.success(byId);
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Brand Brand) {
        return toAxios(brandService.add(Brand));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Brand Brand) {
        return  toAxios(brandService.update(Brand));

    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
       return toAxios(brandService.deleteBy(id));

    }


}
