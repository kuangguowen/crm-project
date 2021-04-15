package com.kgw.controller;

import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Category;
import com.kgw.http.AxiosResult;
import com.kgw.service.CategoryService;
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
@RequestMapping("Category")
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @GetMapping
    public AxiosResult<List<Category>> list() {
        List<Category> list = categoryService.list();
        return AxiosResult.success(list);
    }

    @GetMapping("{id}")
    public AxiosResult<Category> searchById(@PathVariable Long id) {
        Category byId = categoryService.getById(id);
        return AxiosResult.success(byId);
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Category Category) {
        return toAxios(categoryService.add(Category));

    }


    @PutMapping
    public AxiosResult<Void> update(@RequestBody Category Category) {
        return  toAxios(categoryService.update(Category));

    }


    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
       return toAxios(categoryService.deleteBy(id));

    }


}
