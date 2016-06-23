package com.luosl.akhasi.controller.base;

import com.luosl.akhasi.domain.User;
import com.luosl.akhasi.domain.base.EntryDao;
import com.luosl.akhasi.domain.base.DaoContext;
import com.luosl.akhasi.domain.base.Entry;
import com.luosl.akhasi.lucence.LuceneSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Created by Administrator on 2016/5/25.
 */
public class Controller {

    @Resource DaoContext daoContext;
    @Resource protected LuceneSupport blogSupport;
    public static final ThreadLocal<RequestParams> REQUEST_PARAMS = new ThreadLocal<RequestParams>();

    public <T extends Entry> EntryDao<T> getDao(Class<T> clazz){
        return daoContext.getDao(clazz);
    }

    public User getLoginUser(){
        User user = (User) getSession().getAttribute("loginUser");
        if(null == user){
            user = getDao(User.class).findById(1);
        }
        return user;
    }

    public void login(User user){
        getSession().setAttribute("loginUser",user);
    }

    public HttpSession getSession(){
        RequestParams reqParams = REQUEST_PARAMS.get();
        if( null == reqParams ){
            throw new RuntimeException("不能获取session，请检查是否配置ControllerInterceptor");
        }
        return reqParams.req.getSession();
    }

    public HttpServletRequest getRequest(){
        return REQUEST_PARAMS.get().req;
    }

    public HttpServletResponse getResponse(){
        return REQUEST_PARAMS.get().resp;
    }

    public String redirect(String str){
        StringBuilder resp = new StringBuilder("redirect:")
                .append(str);
        return resp.toString();
    }
}
