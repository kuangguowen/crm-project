package com.kgw.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/2 22:05
 */
@Data  // 代替get set 方法
@AllArgsConstructor // 有参构造
@NoArgsConstructor // 无参构造
public class PageResult<T> {
    private long total;

    private List<T> data;

}
