package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp =(HttpServletResponse) servletResponse;
        // 获取请求URL
        String url =  req.getRequestURL().toString();
        log.info("请求的url：{}",url);
        // 判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if(url.contains("login")){
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 获取请求头中的令牌（token）
        String jwt = req.getHeader("token");
        // 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换 对象--JSON------------------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        // 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) { // jwt令牌解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换 对象--JSON------------------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        // 放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
