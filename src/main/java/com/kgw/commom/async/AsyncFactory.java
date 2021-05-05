package com.kgw.commom.async;

import com.kgw.commom.utils.ServiceUtils;
import com.kgw.commom.utils.SpringContainerUtils;
import com.kgw.domin.entity.LoginLog;
import com.kgw.mapper.LoginLogMapper;
import java.time.LocalDateTime;

/**
 * @version 1.8
 * @Author kgw     生产方法
 * @Date: 2021/4/26 21:54
 */
public class AsyncFactory {

    /**
     * 异步发邮件方法
     *
     * @param email
     * @param content
     * @return
     */
    public static Runnable sendEmail(String email, String content) {
        Runnable runnable = () -> {

        };
        return runnable;
    }

    /**
     * 异步添加日志方法
     */
    public static Runnable insertLoginLog(int status, String adminName, String message) {
        LoginLogMapper bean = SpringContainerUtils.getBean(LoginLogMapper.class);
        LoginLog loginLog = new LoginLog()
                .setAdminName(adminName)
                .setLoginStatus(status)
                .setMessage(message)
                .setLoginTime(LocalDateTime.now())
                .setRequestIp(ServiceUtils.getRequestIp(ServiceUtils.getRequest()))
                .setOsName(ServiceUtils.getUserAgent(ServiceUtils.getRequest()).getOperatingSystem().getName())
                .setBroswerName(ServiceUtils.getUserAgent(ServiceUtils.getRequest()).getBrowser().getName())
                .setLoginAddress(ServiceUtils.getLogAddress(ServiceUtils.getRequest()));

        Runnable runnable = () -> {
            bean.insert(loginLog);
        };
        return runnable;
    }


}
