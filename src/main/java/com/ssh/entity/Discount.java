package com.ssh.entity;

import javax.persistence.*;

@Entity
public class Discount {
    private Integer discountType;
    private Integer discountPrice;
    private Integer discountLeast;
    private String discountRule;

    @Id
    @GeneratedValue
    @Column(name = "discountType", nullable = false)
    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    @Basic
    @Column(name = "discountPrice", nullable = true, precision = 0)
    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Basic
    @Column(name = "discountLeast", nullable = true, precision = 0)
    public Integer getDiscountLeast() {
        return discountLeast;
    }

    public void setDiscountLeast(Integer discountLeast) {
        this.discountLeast = discountLeast;
    }

    @Basic
    @Column(name = "discountRule", nullable = true, length = 20)
    public String getDiscountRule() {
        return discountRule;
    }

    public void setDiscountRule(String discountRule) {
        this.discountRule = discountRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (discountType != null ? !discountType.equals(discount.discountType) : discount.discountType != null)
            return false;
        if (discountPrice != null ? !discountPrice.equals(discount.discountPrice) : discount.discountPrice != null)
            return false;
        if (discountLeast != null ? !discountLeast.equals(discount.discountLeast) : discount.discountLeast != null)
            return false;
        if (discountRule != null ? !discountRule.equals(discount.discountRule) : discount.discountRule != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = discountType != null ? discountType.hashCode() : 0;
        result = 31 * result + (discountPrice != null ? discountPrice.hashCode() : 0);
        result = 31 * result + (discountLeast != null ? discountLeast.hashCode() : 0);
        result = 31 * result + (discountRule != null ? discountRule.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "dT:" + discountType.toString();
    }
}
