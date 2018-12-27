package com.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Discountdetail {
    private int discountId;
    private String customerId;
    private Integer discountType;
    private Timestamp discountDate;
    private String discountStatus;

    @Id
    @Column(name = "discountId", nullable = false)
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Basic
    @Column(name = "customerId", nullable = true, length = 20)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
    @Column(name = "discountDate", nullable = true)
    public Timestamp getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(Timestamp discountDate) {
        this.discountDate = discountDate;
    }

    @Basic
    @Column(name = "discountStatus", nullable = true, length = 20)
    public String getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(String discountStatus) {
        this.discountStatus = discountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discountdetail that = (Discountdetail) o;
        return discountId == that.discountId &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(discountType, that.discountType) &&
                Objects.equals(discountDate, that.discountDate) &&
                Objects.equals(discountStatus, that.discountStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountId, customerId, discountType, discountDate, discountStatus);
    }

    @Override
    public String toString(){
        return "did:" + discountId;
    }
}
