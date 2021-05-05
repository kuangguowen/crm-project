package com.kgw.commom.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/27 21:21
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转换string转obj
     */
    public static <T> T str2obj(String str, Class<T> clazz) {

        T t = null;
        try {
            if (str != null) {
                t = objectMapper.readValue(str, clazz);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * obj转string
     */
    public static String obj2str(Object obj) {
        try {
            if (obj != null && obj != " ") {
                return objectMapper.writeValueAsString(obj);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return " ";
    }


    /**
     * 字符串转集合
     */
    public static <T> List<T> str2List(String str, Class<T> clazz) {
        CollectionLikeType collectionLikeType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            List<T> list = objectMapper.readValue(str, collectionLikeType);
            return list;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
