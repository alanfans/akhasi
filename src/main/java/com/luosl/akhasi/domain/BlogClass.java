package com.luosl.akhasi.domain;

import com.luosl.akhasi.domain.base.Entry;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/31.
 */
public class BlogClass extends Entry{

    private int id;
    private String className;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
