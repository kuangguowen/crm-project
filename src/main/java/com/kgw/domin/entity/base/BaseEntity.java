package com.kgw.domin.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:30
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 管理员id
     */

    @TableId(value = "id", type = IdType.AUTO)
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


    public void setData(){
        if (id==null){
            //添加
            this.createBy=1L;
            this.createTime=LocalDateTime.now();
        }else {
            //修改
            this.updateBy=2L;
            this.updateTime=LocalDateTime.now();
        }

    }



}
