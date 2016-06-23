package com.luosl.akhasi.controller.manager;

import com.luosl.akhasi.controller.base.Controller;
import com.luosl.akhasi.domain.Blog;
import com.luosl.akhasi.domain.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Created by Administrator on 2016/5/30.
 */
@org.springframework.stereotype.Controller
@RequestMapping("manager")
public class Manager extends Controller{
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("pager",getDao(Blog.class).findByPage(1,"select * from tbl"));
        return "manager/index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "manager/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String userName,String passwd){
        User user = getDao(User.class).findSingle("select * from tbl where userName = ? and passwd = ? and type = 1"
                ,userName,passwd);
        if(null==user){
            return redirect("login");
        }else {
            login(user);
        }
        return redirect("index");
    }
}
