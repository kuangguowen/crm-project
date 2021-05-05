package com.kgw.commom.perm;

import com.kgw.commom.exception.PermException;
import com.kgw.commom.http.EnumStatus;
import com.kgw.commom.utils.ServiceUtils;
import com.kgw.commom.utils.TokenServiceUtils;
import com.kgw.domin.entity.Menu;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/5/2 17:42
 */
@Aspect
@Component
public class MyAspect {

    @Autowired
    private TokenServiceUtils tokenServiceUtils;

    @Before("pointCut()")
    public void checkPerm(JoinPoint joinPoint) throws IOException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HasPerm annotation = method.getAnnotation(HasPerm.class);
        if (annotation!=null){
            List<Menu> perms = tokenServiceUtils.getLoginAdmin().getPerms();
            String perm = annotation.perm();
            boolean b = perms.stream().anyMatch(menu -> perm.equals(menu.getPermission()));
            if (!b){
                //无权限 抛出异常
                throw  new PermException(EnumStatus.NO_ACCESS);
            }

        }
    }

    /**
     * 切入点表达式
     */
    @Pointcut("@annotation(com.kgw.commom.perm.HasPerm)")
    public void pointCut(){

    }

}
