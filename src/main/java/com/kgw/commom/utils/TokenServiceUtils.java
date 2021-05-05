package com.kgw.commom.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.kgw.commom.cache.CaCheService;
import com.kgw.commom.cache.JsonUtils;
import com.kgw.commom.cache.KeyUtils;
import com.kgw.domin.entity.LoginAdmin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/28 22:45
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TokenServiceUtils {

    private static final String secret = "af21a3s1f3asf13a1fa";

    private final CaCheService caCheService;

    /**
     * 生成token
     */
    public String createToken(String uuid) {
        try {
//            Date expiration_time = new Date(System.currentTimeMillis() + TIME_OUT);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("Java人")              //设置签发人
                    .withSubject("Java都是人上人")      //设置主题
                    .withClaim("uuid", uuid)    //可设置自定义属性
//                    .withExpiresAt(expiration_time)  //设置过期时间
                    .sign(algorithm); //jwt签名
            return token;
        } catch (Exception e) {
            log.error("token生成失败");
        }
        return " ";
    }


    /**
     * 获取token
     */
    public DecodedJWT getDecodedJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Java人")              //设置签发人
                    .build(); //jwt签名
            DecodedJWT verify = verifier.verify(token);
            return verify;
        } catch (Exception e) {
            log.error("token解析失败");
        }
        return null;
    }


    /**
     * 获取到对应的uuid
     */
    public String getUUIDFromToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String s = authorization.split(" ")[1];
        DecodedJWT decodedJWT = getDecodedJWT(s);
        String uuid = decodedJWT.getClaim("uuid").asString();
        System.out.println(uuid);
        return uuid;
    }


    /**
     * 储存token
     */
    public String setLoginAdminAndCreateToken(LoginAdmin loginAdmin){
        String uuid = UUID.randomUUID().toString();
        loginAdmin.setUuid(uuid);
        //缓存用户信息
        caCheService.setCache(KeyUtils.LOGIN_ADMIN+uuid, JsonUtils.obj2str(loginAdmin));
        return createToken(uuid);
    }

    /**
     * 获取登录人的信息
     */
    public LoginAdmin getLoginAdmin(){
        String uuidFromToken = getUUIDFromToken(ServiceUtils.getRequest());
        String cache = caCheService.getCache(KeyUtils.LOGIN_ADMIN+uuidFromToken);
        LoginAdmin loginAdmin = JsonUtils.str2obj(cache, LoginAdmin.class);
        return loginAdmin;
    }

    /**
     * 专门获取用户id
     */
    public Long getAdminId(){
        return getLoginAdmin().getAdmin().getId();
    }


    /**
     * 是否是管理员
     */
    public boolean isAdmin(){
        return getLoginAdmin().getAdmin().getIsAdmin();
    }


    /**
     *缓存用户信息
     */
    public void setLoginAdmin(LoginAdmin loginAdmin){
        caCheService.setCache(KeyUtils.LOGIN_ADMIN+loginAdmin.getUuid(), JsonUtils.obj2str(loginAdmin));

    }


}
