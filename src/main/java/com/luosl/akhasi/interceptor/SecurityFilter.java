package com.luosl.akhasi.interceptor;

import com.luosl.akhasi.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/1.
 */
public class SecurityFilter implements Filter{

    private Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("loginUser");
        String reqUrl = req.getRequestURI();
        if( (null!=user&&user.getUserType()==1)||reqUrl.endsWith("/manager/login") ){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            logger.error("权限错误");
            resp.sendRedirect(req.getContextPath()+"/manager/login");
        }

    }

    public void destroy() {

    }
}
