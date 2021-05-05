package com.kgw.domin.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kgw.commom.Valid.AddGroup;
import com.kgw.commom.Valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    @Null(groups = AddGroup.class,message = "添加不能有id") // 添加时 id必须没有
    @NotNull(groups = UpdateGroup.class,message = "修改时必须要有id") // 修改时 id必须有
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


    public void setData() {
        if (id == null) {
            //添加
            this.createBy = 1L;
            this.createTime = LocalDateTime.now();
        } else {
            //修改
            this.updateBy = 2L;
            this.updateTime = LocalDateTime.now();
        }

    }


}
