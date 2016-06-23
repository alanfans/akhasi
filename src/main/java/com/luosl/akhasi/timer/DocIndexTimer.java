package com.luosl.akhasi.timer;

import com.luosl.akhasi.domain.Blog;
import com.luosl.akhasi.domain.base.DaoContext;
import com.luosl.akhasi.domain.base.Pager;
import com.luosl.akhasi.lucence.LuceneSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/8.
 */
@Component
public class DocIndexTimer {

    private Logger logger = LoggerFactory.getLogger(DocIndexTimer.class);
    @Resource private DaoContext daoContext;
    @Resource private LuceneSupport blogSupport;

    @Scheduled(cron="0 5 * * * ?")
    public void commitIndex() throws IOException {
        blogSupport.commit();
    }

    @Scheduled(cron="30 10 1 * * ?")
    @PostConstruct
    public void indexBlogDoc() throws IOException {
        int size = 5000;
        String sql = "select * from tbl";
        logger.info("--索引Blog开始--");
        blogSupport.deleteAll();
        logger.info("--清空索引完毕，正在重新构建索引--");
        Pager<Blog> page = daoContext.getDao(Blog.class).findByPage(1, size, sql);
        int tital = page.getTotalPage();
        for(int i=1;i<=tital;i++){
            page = daoContext.getDao(Blog.class).findByPage(i, size, sql);
            for(Blog blog:page.getDataList()){
                blogSupport.index(Blog.toDoc(blog));
            }
            logger.info("--已经索引"+page.getDataList().size()+"条数据--");
        }
        blogSupport.commit();
    }

}
