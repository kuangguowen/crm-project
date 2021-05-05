package com.kgw.controller;

import com.kgw.commom.async.AsyncFactory;
import com.kgw.commom.async.AsyncManager;
import com.kgw.commom.cache.CaCheService;
import com.kgw.commom.cache.KeyUtils;
import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.http.EnumStatus;
import com.kgw.commom.utils.TokenServiceUtils;
import com.kgw.controller.base.BaseController;
import com.kgw.domin.entity.Admin;
import com.kgw.domin.entity.LoginAdmin;
import com.kgw.service.AdminService;
import com.kgw.commom.utils.UploadUtils;
import com.wf.captcha.SpecCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/21 19:54
 */
@RestController
@RequestMapping("common")
@RequiredArgsConstructor
public class CommonController extends BaseController {

    private final CaCheService caCheService;

    private final AdminService adminService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TokenServiceUtils tokenServiceUtils;


    /**
     * 文件上传 + 图片校验
     */
    @PostMapping("upload")
    public AxiosResult<String> upload(@PathVariable Part file) throws IOException {
        //判断上传的是不是图片
        BufferedImage read = ImageIO.read(file.getInputStream());
        if (read == null) {
            //不是图片 返回错误状态码
            return AxiosResult.error(EnumStatus.ERROR_NOT_IMAGE);
        }
        // 到这表示是图片
        String filenameExtension = StringUtils.getFilenameExtension(file.getSubmittedFileName());
        // 判断格式是否正确
        if (!"jpg".equalsIgnoreCase(filenameExtension) && !"png".equalsIgnoreCase(filenameExtension)) {
            // 格式不正确
            AxiosResult.error(EnumStatus.ERROR_FORM);
        }
        long size = file.getSize() / 1024;
        if (size > 200) {
            AxiosResult.error(EnumStatus.ERROR_IMAGE_SO_BIG);
        }
        // 到这表示所有的条件都满足 进行上传文件
        String upload = UploadUtils.upload(file);
        return AxiosResult.success(upload);
    }


    /**
     * 获取验证码
     */
    @GetMapping("getCaptcha/{uuid}")
    public AxiosResult<String> getCaptcha(@PathVariable String uuid) {
        System.out.println(uuid);
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 6);
        String verCode = specCaptcha.text().toLowerCase();
        // 存入redis并设置过期时间为2分钟
        caCheService.setCacheWithDefaultTime(KeyUtils.CODE_PREFIX + uuid, verCode);
        // 将key和base64返回给前端
        System.out.println(verCode);
        return AxiosResult.success(specCaptcha.toBase64());
    }

    /**
     * 登录
     */
    @PostMapping("doLogin")
    public AxiosResult<String> doLogin(@RequestBody Map<String, String> map) {
        // 获取与前端相对应的参数
        String username = map.get("username");
        String password = map.get("password");
        String uuid = map.get("uuid");
        String captcha = map.get("captcha");
        //验证码是否正确
        String cache = caCheService.getCache(KeyUtils.CODE_PREFIX + uuid);
        if (!cache.equalsIgnoreCase(captcha)) {
            //验证码错误  存储登录日志
            AsyncManager.getInstance().executor(AsyncFactory.insertLoginLog(1,username,EnumStatus.ERROR_CODE.getMessage()));
            return AxiosResult.error(EnumStatus.ERROR_CODE);
        }
        //判断用户名是否存在
        Admin admin = adminService.getUserByAdmin(username);
        if (admin == null) {
            //用户不存在
            AsyncManager.getInstance().executor(AsyncFactory.insertLoginLog(1,username,EnumStatus.LOGIN_DEFEATED.getMessage()));
            return AxiosResult.error(EnumStatus.LOGIN_DEFEATED);
        }
        //判断密码是否正确
        boolean pwd = bCryptPasswordEncoder.matches(password, admin.getAdminPwd());
        if (!pwd) {
            //密码错误
            AsyncManager.getInstance().executor(AsyncFactory.insertLoginLog(1,username,EnumStatus.PASSWORD_ERROR.getMessage()));
            return AxiosResult.error(EnumStatus.PASSWORD_ERROR);
        }
        //到这表示登录成功
        //清除缓存
        caCheService.clearCache(KeyUtils.CODE_PREFIX + uuid);
        AsyncManager.getInstance().executor(AsyncFactory.insertLoginLog(0,username,"登录成功"));

        //登录成功 保存登录用户信息
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.setAdmin(admin);

        return AxiosResult.success(tokenServiceUtils.setLoginAdminAndCreateToken(loginAdmin));
    }

    /**
     * 获取用户信息
     */
    @GetMapping("getAdminInfo")
    public AxiosResult<Map<String,Object>> getAdminInfo(){
       Map<String,Object> map = adminService.getAdminInfo();
        return AxiosResult.success(map);
    }

}
