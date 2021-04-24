package com.kgw.commom.Transfer;

import com.kgw.commom.reflect.ReflectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/18 20:40
 */
public class TransferUtils {

    /**
     * 通过父id等于0 从一级开始往下排的方法
     */
    public static <T> List<T> buildTree(List<T> list) {
        // 获取到一级分类
        List<T> root = list.stream().filter(item -> (Long) ReflectionUtils.getFieldValue(item, "parentId") == 0).collect(Collectors.toList());
        list.removeAll(root);
        // 第二次递归调用时 把已经获取到的数据删除
        root.forEach(item -> {
            getChildren(item, list);
        });
        return root;
    }


    public static <T> void getChildren(T t, List<T> list) {
        if (hasChildren(t, list)) {
            List<T> collect = list.stream().filter(item -> (Long) ReflectionUtils.getFieldValue(item, "parentId") == (long) ReflectionUtils.getFieldValue(t, "id")).collect(Collectors.toList());
            if (collect != null && collect.size() > 0) {
                ReflectionUtils.setFieldValue(t, "children", collect);
                // 第二次递归调用时 把已经获取到的数据删除
                list.removeAll(collect);
                collect.forEach(item1 -> getChildren(item1, list));
            }
        }
    }


    /**
     * 判断是否有孩子属性  hasChildren
     *
     * @param t
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean hasChildren(T t, List<T> list) {
        return list.stream().anyMatch(item -> {
            long a = (Long) ReflectionUtils.getFieldValue(item, "parentId");
            long b = (Long) ReflectionUtils.getFieldValue(t, "id");
            return a == b;
        });
    }
}



