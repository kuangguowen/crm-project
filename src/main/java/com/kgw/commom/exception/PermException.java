package com.kgw.commom.exception;

import com.kgw.commom.http.EnumStatus;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/5/2 20:43
 */
public class PermException extends RuntimeException {

    private EnumStatus enumStatus;

    public PermException(EnumStatus enumStatus){
        this.enumStatus = enumStatus;
    }

    public EnumStatus getEnumStatus(){
        return enumStatus;
    }
}
