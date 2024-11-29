package com.mmall.pojo;

import java.util.Date;

public class Category {
    private Long id;

    private Integer parentid;

    private String name;

    private Byte status;

    private Integer sortorder;

    private Date createtime;

    private Date updatetime;

    public Category(Long id, Integer parentid, String name, Byte status, Integer sortorder, Date createtime, Date updatetime) {
        this.id = id;
        this.parentid = parentid;
        this.name = name;
        this.status = status;
        this.sortorder = sortorder;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Category() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}