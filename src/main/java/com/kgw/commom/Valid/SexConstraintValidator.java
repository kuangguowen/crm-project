package com.kgw.commom.Valid;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/26 11:58
 */
public class SexConstraintValidator implements ConstraintValidator<SexList,Integer> {

    List<Integer> list;

    /**
     * 初始化执行
     * @param constraintAnnotation
     */
    @Override
    public void initialize(SexList constraintAnnotation) {
        // 拿到了代码当中指定的 0,1,2
        int[] sex = constraintAnnotation.sex();
        list = CollectionUtils.arrayToList(sex);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {

        return list.contains(value);
    }
}
