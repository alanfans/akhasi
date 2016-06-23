package com.luosl.akhasi.controller.manager;

import com.luosl.akhasi.domain.Blog;
import com.luosl.akhasi.domain.BlogClass;
import com.luosl.akhasi.domain.Reply;
import com.luosl.akhasi.domain.base.EntryExeption;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/30.
 */
@Controller
@RequestMapping("manager/blog")
public class BlogManager extends Manager{

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public Object addBlog(Blog blog) throws IOException {
        blog.setAuthor(getLoginUser().getUserName());
        blog.setCreateTime(new Date());
        blog.setBlogClassName(getDao(BlogClass.class).
                findById(blog.getBlogClassId()).getClassName());
        int blogId = getDao(Blog.class).save(blog);
        blogSupport.index( Blog.toDoc(getDao(Blog.class).findById(blogId)) );
        return new Object();
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("blogClass",getDao(BlogClass.class).findList("select * from tbl"));
        return "manager/blogAdd";
    }

    @RequestMapping(value = "delete")
    @Transactional(readOnly = false)
    @ResponseBody
    public Object delete(int blogId){
        getDao(Blog.class).deleteById(blogId);
        getDao(Reply.class).update("delete from tbl where blogId = ?",blogId);
        return new Object();
    }

    @RequestMapping("list/{currentPage}")
    public String listBlog(@PathVariable int currentPage, Model model){
        if(currentPage<1){
            currentPage = 1;
        }
        model.addAttribute("pager",getDao(Blog.class).findByPage(currentPage,"select * from tbl"));
        return "manager/blogList";
    }
}
