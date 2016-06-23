package com.luosl.akhasi.controller.web;

import com.luosl.akhasi.controller.base.Controller;
import com.luosl.akhasi.domain.Blog;
import com.luosl.akhasi.domain.BlogClass;
import com.luosl.akhasi.domain.Reply;
import com.luosl.akhasi.domain.User;
import com.luosl.akhasi.domain.base.EntryExeption;
import com.luosl.akhasi.domain.base.Pager;
import com.luosl.akhasi.utils.HtmlUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2016/5/25.
 */
@org.springframework.stereotype.Controller
@RequestMapping("blog")
public class BolgController extends Controller {


    @RequestMapping("get/{id}")
    public String get(@PathVariable int id,Model model){
        return get(id,1,model);
    }

    @RequestMapping("get/{id}/{replyPage}")
    public String get(@PathVariable int id,@PathVariable int replyPage,Model model){
        model.addAttribute("blog",getDao(Blog.class).findById(id) );
        Pager<Reply> pageReply = getDao(Reply.class).findByPage(replyPage,
                "select * from tbl where blogId = ? and rootId is null order by id asc", id);
        model.addAttribute("pager",pageReply);
        for(Reply reply:pageReply.getDataList()){
            reply.setOwner(getDao(User.class).findById(reply.getOwnerId()));
            reply.setToUser(getDao(User.class).findById(reply.getToUserId()));
            List<Reply> crs = getDao(Reply.class).
                    findList("select * from tbl where rootId = ?", reply.getId());
            for (Reply cr:crs){
                cr.setOwner(getDao(User.class).findById(cr.getOwnerId()));
                cr.setToUser(getDao(User.class).findById(cr.getToUserId()));
            }
            reply.setChilds(crs );
        }
        return "web/blog";
    }

    @RequestMapping("publishReply")
    @ResponseBody
    public Object publishReply(Reply reply) throws EntryExeption {
        String content = reply.getContent();
        if(!StringUtils.isBlank(content)){
            reply.setContent(HtmlUtils.cleanScriptTag(content));
            reply.setOwnerId(getLoginUser().getId());
            reply.setCreateTime(new Date());
            getDao(Reply.class).save(reply);
        }
        return new Object();
    }

    @RequestMapping("blogClass/{blogClassId}/{currentPage}")
    public String blogClass(@PathVariable int blogClassId,@PathVariable int currentPage,Model model){
        model.addAttribute("pager",
                getDao(Blog.class).findByPage(currentPage,
                        "select * from tbl where blogClassId = ? order by id desc",blogClassId) );
        model.addAttribute("blogClass",getDao(BlogClass.class).findById(blogClassId));
        return "web/classify";
    }

    @RequestMapping("search")
    public String search(String keyWords,Model model) throws IOException, ParseException {
        return search(keyWords,1,model);
    }

    @RequestMapping("search/{keyWords}/{currentPage}")
    public String search(@PathVariable String keyWords,@PathVariable int currentPage,Model model)
            throws IOException, ParseException {
        Pager<Document> docPage = blogSupport.page(10, currentPage, keyWords,"title", "content");
        String sql = "select * from tbl where id in(?,?,?,?,?,?,?,?,?,?)";
        Integer[] params = new Integer[10];
        int count = 0;
        for(Document doc:docPage.getDataList()){
            params[count] = doc.getField("id").numericValue().intValue();
            count++;
        }
        Pager<Blog> pager = new Pager<Blog>();
        pager.setCurrentPage(docPage.getCurrentPage());
        pager.setTotalCount(docPage.getTotalCount());
        pager.setPageSize(docPage.getPageSize());
        pager.setDataList(getDao(Blog.class).findList(sql,params));
        model.addAttribute("pager",pager);
        model.addAttribute("keyWords",keyWords);
        return "web/search";
    }
}
