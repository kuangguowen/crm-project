package com.kgw.controller;

import com.kgw.commom.http.AxiosResult;
import com.kgw.controller.base.BaseController;
import com.kgw.utils.UploadUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Part;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/21 19:54
 */
@RestController
@RequestMapping("common")
public class uploadController extends BaseController {

    /**
     * 文件上传
     *
     * @param
     * @return
     */
    @PostMapping("upload")
    public AxiosResult<String> upload(@PathVariable Part file) {
        String upload = UploadUtils.upload(file);
        return AxiosResult.success(upload);
    }

}
