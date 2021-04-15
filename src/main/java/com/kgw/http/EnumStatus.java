package com.kgw.http;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/3/31 10:01
 */
public enum  EnumStatus {
    OK(20000,"运行成功"),
    ERROR(40000,"运行失败"),
    NO_LOGIN(45000,"未登录"),
    LOGIN_DEFEATED(60000,"账户名或邮箱不正确"),
    ERROR_CODE(50000,"验证码不正确"),
    ERROR_CODE_OVER_TIME(50000,"验证码失效"),
    NO_ACTIVE(11111,"用户未激活")
    ;

    private int status;
    private String message;

    EnumStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
