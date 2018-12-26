package com.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Shopdiscount {
    private int id;
    private int discountType;
    private String shopId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "discountType", nullable = true, length = 20)
    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    @Basic
    @Column(name = "shopId", nullable = true, length = 20)
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopdiscount that = (Shopdiscount) o;
        return id == that.id &&
                Objects.equals(discountType, that.discountType) &&
                Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discountType, shopId);
    }
}
