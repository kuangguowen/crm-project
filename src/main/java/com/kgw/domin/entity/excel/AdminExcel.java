package com.kgw.domin.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kgw.commom.LocalDataStringConverter;
import lombok.Data;

import java.net.URL;
import java.time.LocalDateTime;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/25 22:10
 */
@Data
@ContentRowHeight(25) // 行高
@HeadRowHeight(40)    // 头高
@ColumnWidth(20)      // 列宽
public class AdminExcel {


    @TableId(value = "id", type = IdType.AUTO)
    @ExcelProperty(value = "员工id", index = 0)
    private Long id;


    @ExcelProperty("员工名称")
    private String adminName;


    @ExcelProperty("员工昵称")
    private String nickName;


    @ExcelProperty("员工性别")
    private String sex;


    @ExcelProperty("员工手机")
    private String adminPhone;


    @ExcelProperty("员工邮箱")
    private String adminEmail;


    @ExcelProperty("所在部门")
    private String deptName;


    @ColumnWidth(50)
    @ExcelProperty("员工地址")
    private String adminAddress;


    @ExcelProperty("是否可用")
    private String isActive;


    @ColumnWidth(7)
    @ExcelProperty("员工头像")
    private transient URL url;


    @ExcelProperty(value = "入职时间",converter = LocalDataStringConverter.class)
    private LocalDateTime createTime;


}
