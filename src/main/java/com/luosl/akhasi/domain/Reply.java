package com.luosl.akhasi.domain;

import com.luosl.akhasi.domain.base.Entry;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public class Reply extends Entry{
    private Integer id;
    private Integer blogId;
    private Integer rootId;
    private String content;
    private Date createTime;
    private Integer ownerId;
    private Integer toUserId;
    private List<Reply> childs;
    private User owner;
    private User toUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public List<Reply> getChilds() {
        return childs;
    }

    public void setChilds(List<Reply> childs) {
        this.childs = childs;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    @Override
    public String _notSqlField() {
        return "childs,owner,toUser";
    }
}
