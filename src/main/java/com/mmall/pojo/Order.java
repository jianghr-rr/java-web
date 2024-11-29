package com.mmall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private Long orderno;

    private Integer userid;

    private Integer shippingid;

    private BigDecimal payment;

    private Integer paymenttype;

    private Integer postage;

    private Integer status;

    private Date paymenttime;

    private Date sendtime;

    private Date endtime;

    private Date closetime;

    private Date createtime;

    private Date updatetime;

    public Order(Long id, Long orderno, Integer userid, Integer shippingid, BigDecimal payment, Integer paymenttype, Integer postage, Integer status, Date paymenttime, Date sendtime, Date endtime, Date closetime, Date createtime, Date updatetime) {
        this.id = id;
        this.orderno = orderno;
        this.userid = userid;
        this.shippingid = shippingid;
        this.payment = payment;
        this.paymenttype = paymenttype;
        this.postage = postage;
        this.status = status;
        this.paymenttime = paymenttime;
        this.sendtime = sendtime;
        this.endtime = endtime;
        this.closetime = closetime;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Order() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getShippingid() {
        return shippingid;
    }

    public void setShippingid(Integer shippingid) {
        this.shippingid = shippingid;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Integer paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
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