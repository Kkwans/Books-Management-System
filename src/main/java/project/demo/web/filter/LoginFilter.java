package project.demo.web.filter;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import project.demo.web.domain.Result;
import project.demo.web.utils.JwtUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*") //拦截所有请求
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        //前置：强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求url
        String url = request.getRequestURL().toString();


        //2.设置与登录注册相关的资源进入白名单
        String[] urls = {"/login","/register","/imgs/","/css/","/js/","/plugins/","/checkName","/getCheckCode","/pleaseLogin","/favicon.ico"};
        //遍历判断url是否在白名单内,若在，放行
        for (String s : urls) {
            if(url.contains(s)){
                //在白名单内，放行
                System.out.println("白名单放行===>"+url);
                chain.doFilter(request,response);
                return;
            }
            if(url.equals("http://localhost/")||url.equals("http://192.168.1.103/")) return;
        }
        System.out.println("拦截请求===>"+url);

        //3.获取请求头中的令牌（token）
        String jwt = request.getHeader("jwt");
        System.out.println("jwt令牌:"+jwt);

        //admin主页获取Cookie中的jwt
        if(jwt==null&&url.contains("/admin")){
            System.out.println("admin主页请求，获取Cookie中的jwt:");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("jwt".equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        System.out.println(jwt);
                        break;
                    }
                }
            }
        }


        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        //!StringUtils.hasLength(jwt)
        if(jwt==null||jwt.isEmpty()){
            System.out.println("用户未登录，跳转登录界面...");
            //跳转登录页面
            response.sendRedirect("http://192.168.1.103/pleaseLogin");
            /*Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);*/
            return;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            Claims claims = JwtUtil.parseJWT(jwt);
            System.out.println("JWT令牌解析数据:"+claims);
        }catch (Exception e){
            System.out.println("令牌解析失败");
            response.sendRedirect("http://192.168.1.103/pleaseLogin");

            /*Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);*/
            return;
        }

        //6.放行

        chain.doFilter(request, response);
    }
}
