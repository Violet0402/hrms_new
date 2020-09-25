package com.sumaojin.suzhenping.hrms.interceptor;

import com.sumaojin.suzhenping.hrms.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 配置拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
        /*try{
            String token = request.getHeader("token");
            Claims claimByToken = JwtUtils.getClaimByToken(token);
        }catch (Exception e){

        }*/

    }
}
