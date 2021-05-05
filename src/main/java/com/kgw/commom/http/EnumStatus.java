package com.kgw.commom.http;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/3/31 10:01
 */
public enum  EnumStatus {
    OK(20000,"运行成功"),
    ERROR(40000,"运行失败"),
    NO_LOGIN(45000,"未登录"),
    LOGIN_DEFEATED(60000,"账户名不正确"),
    ERROR_CODE(50000,"验证码不正确"),
    ERROR_CODE_OVER_TIME(51000,"验证码失效"),
    NO_ACTIVE(11111,"用户未激活"),
    FORM_VALIDATION_FAILED(44444,"表单验证失败"),
    ERROR_NOT_IMAGE(55555,"上传的不是图片"),
    ERROR_FORM(51111,"图片上传格式错误"),
    ERROR_IMAGE_SO_BIG(52222,"上传的图片太大"),
    PASSWORD_ERROR(88888,"密码错误"),
    NO_ACCESS(12345,"无权限操作")
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
