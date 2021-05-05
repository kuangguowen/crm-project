package com.kgw.commom.exception;

import com.kgw.commom.http.AxiosResult;
import com.kgw.commom.http.EnumStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.8
 * @Author kgw    (异常处理类)
 * @Date: 2021/4/26 10:55
 */
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 表单校验
     * @param e
     * @return  进入此方法 表示表单有错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AxiosResult<Map<String,String>> handler(MethodArgumentNotValidException e){
        Map<String,String> map = new HashMap<String, String>();
        BindingResult bindingResult = e.getBindingResult();
        // 判断 如果有错误
        if (bindingResult.hasFieldErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (int i = 0; i < fieldErrors.size(); i++) {
                map.put(fieldErrors.get(i).getField(),fieldErrors.get(i).getDefaultMessage());
            }
        }
        return AxiosResult.error(EnumStatus.FORM_VALIDATION_FAILED,map);
    }


    /**
     * 无操作权限
     * @param e
     * @return
     */
    @ExceptionHandler(PermException.class)
    public AxiosResult<Void> handler(PermException e){
        return AxiosResult.error(EnumStatus.NO_ACCESS);
    }






}
