package com.kgw.controller;

import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.page.PageResult;
import com.kgw.commom.perm.HasPerm;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.LoginLog;
import com.kgw.domin.query.LoginLogCriteria;
import com.kgw.domin.vo.LoginLogVo;
import com.kgw.service.LoginLogService;
import com.kgw.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:59
 */
@RestController
@RequestMapping("loginLog")
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;



    @GetMapping
    public AxiosResult<PageResult<LoginLogVo>> list(LoginLogCriteria loginLogCriteria) {

       return AxiosResult.success(loginLogService.searchPage(loginLogCriteria));
    }

    @GetMapping("{id}")
    public AxiosResult<LoginLogVo> searchById(@PathVariable Long id) {
        LoginLogVo loginLogVo = loginLogService.findById(id);
        return AxiosResult.success(loginLogVo);
    }


    /**
     * 添加
     * @param loginLogVo
     * @return
     */
    @PostMapping
    public AxiosResult<Void> add(@RequestBody LoginLog loginLogVo) {

        return toAxios(loginLogService.add(loginLogVo));

    }

    /**
     * 修改
     * @param loginLogVo
     * @return
     */
    @PutMapping
    public AxiosResult<Void> update(@RequestBody LoginLog loginLogVo) {
        return  toAxios(loginLogService.update(loginLogVo));
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @HasPerm(perm = "loginLog:delete")
    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id){
        System.out.println(id);
       return toAxios(loginLogService.deleteBy(id));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @HasPerm(perm = "loginLog:batch")
    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> batchDelete(@PathVariable List<Long> ids){
        System.out.println(ids);
          return toAxios(loginLogService.batchDelete(ids)) ;

    }


}
