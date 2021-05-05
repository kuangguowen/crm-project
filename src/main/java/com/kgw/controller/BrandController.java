package com.kgw.controller;

import com.github.pagehelper.PageInfo;
import com.kgw.commom.perm.HasPerm;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.base.BaseEntity;
import com.kgw.domin.query.BrandCriteria;
import com.kgw.domin.vo.BrandVo;
import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.page.PageResult;
import com.kgw.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:59
 */
@RestController
@RequestMapping("brand")
public class BrandController extends BaseController {

    @Autowired
    private  BrandService brandService;



    @GetMapping
    public AxiosResult<PageResult<BrandVo>> list(BrandCriteria brandCriteria) {

       return AxiosResult.success(brandService.searchPage(brandCriteria));
    }

    @GetMapping("{id}")
    public AxiosResult<BrandVo> searchById(@PathVariable Long id) {
        BrandVo brandVo = brandService.findById(id);
        return AxiosResult.success(brandVo);
    }

    @HasPerm(perm = "brand:add")
    @PostMapping
    public AxiosResult<Void> add(@RequestBody Brand brand) {

        return toAxios(brandService.add(brand));

    }

    @HasPerm(perm = "brand:edit")
    @PutMapping
    public AxiosResult<Void> update(@RequestBody Brand brand) {

        return  toAxios(brandService.update(brand));

    }

    @HasPerm(perm = "brand:delete")
    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
       return toAxios(brandService.deleteBy(id));

    }
    @HasPerm(perm = "brand:batch")
    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> batchDelete(@PathVariable List<Long> ids){
          return toAxios(brandService.batchDelete(ids)) ;

    }


}
