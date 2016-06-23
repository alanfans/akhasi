package com.luosl.akhasi.controller.web;

import com.luosl.akhasi.controller.base.Controller;
import com.luosl.akhasi.domain.User;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/15.
 */
@org.springframework.stereotype.Controller
@RequestMapping("qq")
public class QQAuthController extends Controller{

    @RequestMapping("login")
    public String login(HttpServletRequest request) throws IOException, QQConnectException {
        return redirect( new Oauth().getAuthorizeURL(request) );
    }

    @RequestMapping("afterLogin")
    public String afterLogin(HttpServletRequest request) throws QQConnectException {
        AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
        String accessToken, openID;
        if (accessTokenObj.getAccessToken().equals("")) {
            System.out.print("没有获取到响应参数");
        } else {
            accessToken = accessTokenObj.getAccessToken();
            OpenID openIDObj = new OpenID(accessToken);
            openID = openIDObj.getUserOpenID();
            User user = getDao(User.class).findSingle("select * from tbl where openId = ?",openID);
            if(null == user){
                // 用户第一次登陆，保存用户信息
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                user = new User();
                user.setOpenId(openID);
                user.setCreateTime(new Date());
                user.setImg(userInfoBean.getAvatar().getAvatarURL100());
                user.setNickName(userInfoBean.getNickname());
                user.setSex(userInfoBean.getGender());
                getDao(User.class).save(user);
            }
            login(user);
        }
        return redirect("/index");
    }

    @RequestMapping("logout")
    public String logout(){
        getSession().removeAttribute("loginUser");
        return redirect("/index");
    }

}
