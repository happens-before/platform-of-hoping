package com.xupt.edu.zwy.platformofhoping.common;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * Description:解决跨越问题
 *
 * @author wanyuezhao
 * @Date 19-3-19
 * @Time 下午4:57
 */

@Component
@WebFilter(urlPatterns = "/*", filterName = "authFilter")
public class PassHttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        filterChain.doFilter(servletRequest, httpResponse);
    }

    @Override
    public void destroy() {
    }
}
