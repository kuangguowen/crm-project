package com.kgw.domin.query.base;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 15:27
 */
@Data
public class BaseQueryCriteria {
    /**
     * 通用查询类   + 分页默认数据
     */
    private int currentPage = 1;
    private int pageSize = 5;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
