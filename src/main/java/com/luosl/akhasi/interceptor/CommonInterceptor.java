package com.luosl.akhasi.interceptor;

import com.luosl.akhasi.domain.BlogClass;
import com.luosl.akhasi.domain.base.DaoContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/5/31.
 */
public class CommonInterceptor implements HandlerInterceptor {
    @Resource DaoContext daoContext;
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(null!=modelAndView){
            modelAndView.addObject("commonBlogClass",
                    daoContext.getDao(BlogClass.class).findAll());
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
