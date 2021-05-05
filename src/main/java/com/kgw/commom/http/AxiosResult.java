package com.kgw.commom.http;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/3/31 9:56
 */
@JsonInclude(JsonInclude.Include.NON_NULL)  // 表示 这个类在转json格式字符串时 如果属性中有null 则json字符串中不会有这个属性
public class  AxiosResult<T> {

    private int status;
    private String massage;
    private T data;

    /**
     *返回成功的方法
     * @param
     * @param
     */
    public static <T> AxiosResult<T>success(){
        return getAxIosResult(EnumStatus.OK,null);
    }

    /**
     * 返回成功携带数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> AxiosResult<T>success(T data){
        return getAxIosResult(EnumStatus.OK,data);
    }

    /**
     * 返回失败的方法
     * @param <T>
     * @param
     * @return
     */
    public static <T> AxiosResult<T>error(EnumStatus enumStatus){
        return getAxIosResult(enumStatus,null);
    }


    public static <T> AxiosResult<T>error(EnumStatus enumStatus,T data){
        return getAxIosResult(enumStatus,data);
    }


    public static <T> AxiosResult<T> error(){
        return getAxIosResult(EnumStatus.ERROR,null);
    }


    /**
     * 返回失败携带数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> AxiosResult<T>error(T data){
        return getAxIosResult(EnumStatus.ERROR,data);
    }





    private static <T> AxiosResult<T> getAxIosResult(EnumStatus enumStatus, T data) {
        return new AxiosResult<T>(enumStatus, data);
    }


    public AxiosResult(EnumStatus enumStatus, T data) {
        this.status = enumStatus.getStatus();
        this.massage = enumStatus.getMessage();
        this.data = data;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
