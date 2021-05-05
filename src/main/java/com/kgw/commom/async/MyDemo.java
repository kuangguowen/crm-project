package com.kgw.commom.async;

import com.kgw.domin.entity.Admin;
import com.kgw.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/26 21:57
 */
@Component
public class MyDemo {

    @Autowired
    private AdminService adminService;

    @Scheduled(cron = "0 5 22 * * *")
    public void sendEmail(){
        List<Admin> list = adminService.list();
        list.forEach(admin -> {
            AsyncManager.getInstance().executor(AsyncFactory.sendEmail(admin.getAdminEmail(),"定时任务"));

        });
    }

}
