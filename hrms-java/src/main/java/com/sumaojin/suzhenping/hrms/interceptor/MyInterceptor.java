package com.sumaojin.suzhenping.hrms.interceptor;

import cn.hutool.json.JSONUtil;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
        String token = request.getHeader("token");
        try{
            Claims claim = JwtUtils.getClaimByToken(token);
            Date date = claim.getExpiration();
            if (JwtUtils.isTokenExpired(date)){
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSONUtil.toJsonStr(new CommonResult(401,"token校验失败")));
                return false;
            }
        }catch (Exception e){
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSONUtil.toJsonStr(new CommonResult(401,"token校验失败")));
            return false;
        }
        return true;
    }

    public void renderJson(HttpServletResponse response, Object jsonObject){
        try {
            response.addHeader("Access-Control-Allow-Origin","*");
            response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSONUtil.toJsonStr(new CommonResult(401,"token校验失败")));
        } catch (IOException e) {

        }

    }
}
