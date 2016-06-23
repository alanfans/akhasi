package com.luosl.akhasi.controller.web;

import com.luosl.akhasi.controller.base.Controller;
import com.luosl.akhasi.domain.Blog;
import com.luosl.akhasi.domain.base.EntryDao;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by Administrator on 2016/5/23.
 */
@org.springframework.stereotype.Controller
public class IndexController extends Controller {

    @RequestMapping("")
    public String index(Model model){
        return index(1,model);
    }

    @RequestMapping("index")
    public String index1(Model model){
        return index(model);
    }

    @RequestMapping("index/{currentPage}")
    public String index(@PathVariable int currentPage, Model model){
        EntryDao<Blog> blogDao = getDao(Blog.class);
        model.addAttribute("pager",
                blogDao.findByPage(currentPage,"select * from tb_blog order by id desc") );
        return "web/index";
    }

    @RequestMapping("qqLogin/callback")
    public String qqLogin(String access_token){

        return "web/qqCallback";
    }

}
