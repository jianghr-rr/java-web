package com.mmall.pojo;

import java.util.Date;

public class Cart {
    private Integer id;

    private Integer userid;

    private Integer productid;

    private Integer quantity;

    private Integer checked;

    private Date createtime;

    private Date updatetime;

    public Cart(Integer id, Integer userid, Integer productid, Integer quantity, Integer checked, Date createtime, Date updatetime) {
        this.id = id;
        this.userid = userid;
        this.productid = productid;
        this.quantity = quantity;
        this.checked = checked;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Cart() {
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

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
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