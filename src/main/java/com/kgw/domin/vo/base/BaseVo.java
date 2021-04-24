package com.kgw.domin.vo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 16:39
 * 反回给前端的基类
 */
@Data
public class BaseVo implements Serializable {

    /**
     * id
     */
    private Long id;


    /**
     * 创建者
     */
    @JsonIgnore
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonIgnore
    private LocalDateTime createTime;

    /**
     * 修改者
     */
    @JsonIgnore
    private Long updateBy;

    /**
     * 修改时间
     */
    @JsonIgnore
    private LocalDateTime updateTime;




}
