package com.kgw.commom.utils;

import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/28 20:28
 */
public class ServiceUtils {

    /**
     * 获取全局request
     */
    public static HttpServletRequest getRequest() {
        // 强转
        ServletRequestAttributes ServletRequestAttributes = (org.springframework.web.context.request.ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ServletRequestAttributes.getRequest();
    }

    /**
     * 获取全局repose
     */
    public static HttpServletResponse getResponse() {
        // 强转
        ServletRequestAttributes ServletRequestAttributes = (org.springframework.web.context.request.ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ServletRequestAttributes.getResponse();
    }


    /**
     * 获取user-agent
     */
    public static UserAgent getUserAgent(HttpServletRequest httpServletRequest) {
        return UserAgent.parseUserAgentString(httpServletRequest.getHeader("user-agent"));
    }


    /**
     * 获取ip
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //如果是 0:0:0:0:0:0:0:1 表示内网 返回可以识别的内网ip 127.0.0.1
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取地理位置
     */
    public static String getLogAddress(HttpServletRequest request) {
        // 通过id获取id地址
        String requestIp = getRequestIp(request);
        RestTemplate bean = SpringContainerUtils.getBean(RestTemplate.class);
        String url = "http://apis.juhe.cn/ip/ipNew?ip=" + requestIp + "&key=3d1141299b9e7c5068bea9c28b9d0008";
        Map forObject = bean.getForObject(url, Map.class);
        Map<String, String> map = (Map<String, String>) forObject.get("result");
        String country = map.get("Country");
        String province = map.get("Province");
        String city = map.get("City");
        return country + province + city;
    }


}
