package com.ssh.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Productdiscount {
    private int discountType;
    private String productId;
    private int id;

    @Basic
    @Column(name = "discountType", nullable = true, length = 20)
    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    @Basic
    @Column(name = "productId", nullable = true, length = 20)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productdiscount that = (Productdiscount) o;
        return id == that.id &&
                Objects.equals(discountType, that.discountType) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountType, productId, id);
    }
}
