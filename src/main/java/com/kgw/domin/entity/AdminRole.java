package com.kgw.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kgw.domin.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author kgw
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_admin_role")
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole{

    private Long adminId;
    private Long roleId;


}
