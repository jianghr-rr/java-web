package com.mmall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Long id;

    private Integer categoryid;

    private String name;

    private String subtitle;

    private String mainimage;

    private String subimages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    public Product(Long id, Integer categoryid, String name, String subtitle, String mainimage, String subimages, String detail, BigDecimal price, Integer stock, Integer status, Date createtime, Date updatetime) {
        this.id = id;
        this.categoryid = categoryid;
        this.name = name;
        this.subtitle = subtitle;
        this.mainimage = mainimage;
        this.subimages = subimages;
        this.detail = detail;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Product() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage == null ? null : mainimage.trim();
    }

    public String getSubimages() {
        return subimages;
    }

    public void setSubimages(String subimages) {
        this.subimages = subimages == null ? null : subimages.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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