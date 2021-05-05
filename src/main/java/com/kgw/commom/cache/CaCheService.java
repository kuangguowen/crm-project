package com.kgw.commom.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/27 21:35
 */
@Component
@RequiredArgsConstructor
public class CaCheService {

    /**
     * 设置默认时间
     */
    public static final long defaultTime = 2;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * （设置有时间限制）
     */
    public void setCacheWithDefaultTime(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, defaultTime, TimeUnit.MINUTES);
    }


    /**
     * （设置有时间限制）自定义
     */
    public void setCacheWithCustomerTime(String key, String value,long minute) {
        stringRedisTemplate.opsForValue().set(key, value, minute,TimeUnit.MINUTES);
    }

    /**
     * 设置缓存
     */
    public void setCache(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }


    /**
     * 获取缓存 判断是否存在
     */
    public boolean exitsKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }


    /**
     * 获取缓存
     */
    public String getCache(String key) {
        if (exitsKey(key)) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return " ";
    }

    /**
     * 删除缓存
     */
    public void clearCache(String key){
        stringRedisTemplate.delete(key);
    }

}
