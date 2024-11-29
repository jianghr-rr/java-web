package com.mmall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {
    private Integer id;

    private Integer userid;

    private Long orderno;

    private Integer productid;

    private String productname;

    private String productimage;

    private BigDecimal currentunitprice;

    private Integer quantity;

    private BigDecimal totalprice;

    private Date createtime;

    private Date updatetime;

    public OrderItem(Integer id, Integer userid, Long orderno, Integer productid, String productname, String productimage, BigDecimal currentunitprice, Integer quantity, BigDecimal totalprice, Date createtime, Date updatetime) {
        this.id = id;
        this.userid = userid;
        this.orderno = orderno;
        this.productid = productid;
        this.productname = productname;
        this.productimage = productimage;
        this.currentunitprice = currentunitprice;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public OrderItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage == null ? null : productimage.trim();
    }

    public BigDecimal getCurrentunitprice() {
        return currentunitprice;
    }

    public void setCurrentunitprice(BigDecimal currentunitprice) {
        this.currentunitprice = currentunitprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
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