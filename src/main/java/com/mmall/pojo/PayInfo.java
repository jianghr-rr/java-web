package com.mmall.pojo;

import java.util.Date;

public class PayInfo {
    private Long id;

    private Integer userid;

    private Long orderno;

    private Integer payplatform;

    private String platformnumber;

    private String platformstatus;

    private Date createtime;

    private Date updatetime;

    public PayInfo(Long id, Integer userid, Long orderno, Integer payplatform, String platformnumber, String platformstatus, Date createtime, Date updatetime) {
        this.id = id;
        this.userid = userid;
        this.orderno = orderno;
        this.payplatform = payplatform;
        this.platformnumber = platformnumber;
        this.platformstatus = platformstatus;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public PayInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public Integer getPayplatform() {
        return payplatform;
    }

    public void setPayplatform(Integer payplatform) {
        this.payplatform = payplatform;
    }

    public String getPlatformnumber() {
        return platformnumber;
    }

    public void setPlatformnumber(String platformnumber) {
        this.platformnumber = platformnumber == null ? null : platformnumber.trim();
    }

    public String getPlatformstatus() {
        return platformstatus;
    }

    public void setPlatformstatus(String platformstatus) {
        this.platformstatus = platformstatus == null ? null : platformstatus.trim();
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