package com.kgw.transfer.base;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 16:57
 */
@Slf4j
public class BaseTransfer<T, VO> {


    private Class<T> clazzT;
    private Class<VO> clazzVO;

    //无参构造
    public BaseTransfer() {
        Type type = this.getClass().getGenericSuperclass();
        Type[] genericType = ((ParameterizedType) type).getActualTypeArguments();
        clazzT= (Class<T>) genericType[0];
        clazzVO= (Class<VO>) genericType[1];
    }

    /**
     * 转VO
     */
  public  VO toVo(T t){
        try {
            VO vo = clazzVO.newInstance();
            BeanUtils.copyProperties(t,vo);
            return  vo;
        } catch (Exception e) {
        log.error("toVo转换错误",t);
        }
        return null;
    }

    /**
     * 转T
     */
    public  T toEntity(VO vo){
        try {
            T t = clazzT.newInstance();
            BeanUtils.copyProperties(vo,t);
            return  t;
        } catch (Exception e) {
            log.error("toEntity转换错误", vo);
        }
        return null;
    }

    /**
     * 转VO
     */
    public List<VO> toVO (List<T> list){
        ArrayList<VO> list1 = new ArrayList<>();
        list.forEach(t -> list1.add(toVo(t)));
        return list1;
    }


    /**
     * 转entity
     */
    public List<T> toT (List<VO> list){
        ArrayList<T> list1 = new ArrayList<>();
        list.forEach(vo -> list1.add(toEntity(vo)));
        return list1;
    }

}
