package com.kgw.domin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kgw.domin.vo.base.BaseVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 16:42
 */
@Data
public class LoginLogVo {

    /**
     * 反回的数据是页面当中有什么就返回什么
     */
    private Long loginId;

    private String adminName;

    private String requestIp;

    private String LoginAddress;

    private String broswerName;

    private String osName;

    private int loginStatus;

    private String message;

    private LocalDateTime loginTime;
}
