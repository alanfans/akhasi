package com.luosl.akhasi.domain;

import com.luosl.akhasi.domain.base.Entry;
import com.luosl.akhasi.utils.HtmlUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/23.
 */
public class Blog extends Entry{

    private Integer id;
    private String author;
    private Date createTime;
    private Integer readCount = 0;
    private String title;
    private String blogType;
    private String abstracts;
    private String blogClassName;
    private int blogClassId;
    private String keyWords;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentDesc(){

        return HtmlUtils.clean(400>content.length()?content:content.substring(0,400));
    }

    public String[] getKeyWordsArray(){
        return (keyWords == null) ? null : keyWords.split(",|，");
    }

    public String getBlogClassName() {
        return blogClassName;
    }

    public void setBlogClassName(String blogClassName) {
        this.blogClassName = blogClassName;
    }

    public int getBlogClassId() {
        return blogClassId;
    }

    public void setBlogClassId(int blogClassId) {
        this.blogClassId = blogClassId;
    }

    @Override
    public String _notSqlField() {
        return "keyWordsArray,contentDesc";
    }

    public static Document toDoc(Blog blog){
        Document doc = new Document();
        TextField tf = new TextField("title", blog.getTitle(), Field.Store.NO);
        tf.setBoost(0.9f);
        TextField cf = new TextField("content", blog.getContent(), Field.Store.NO);
        cf.setBoost(0.1f);
        TextField kf = new TextField("keyWords", blog.getKeyWords(), Field.Store.NO);
        kf.setBoost(0.6f);
        doc.add(new IntField("id",blog.getId(), Field.Store.YES));
        doc.add(tf);
        doc.add(cf);
        doc.add(kf);
        return doc;
    }
}
