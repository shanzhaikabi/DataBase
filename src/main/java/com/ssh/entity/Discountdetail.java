package com.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Discountdetail {
    private int discountId;
    private Timestamp discountDate;
    private String discountStatus;
    private String discountType;

    @Id
    @Column(name = "discountId", nullable = false)
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
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

    @Basic
    @Column(name = "discountType", nullable = true)
    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountDate) {
        this.discountType = discountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discountdetail that = (Discountdetail) o;

        if (discountId != that.discountId) return false;
        if (discountDate != null ? !discountDate.equals(that.discountDate) : that.discountDate != null) return false;
        if (discountStatus != null ? !discountStatus.equals(that.discountStatus) : that.discountStatus != null)
            return false;
        if (discountType != null ? !discountType.equals(that.discountType) : that.discountType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = discountId;
        result = 31 * result + (discountDate != null ? discountDate.hashCode() : 0);
        result = 31 * result + (discountStatus != null ? discountStatus.hashCode() : 0);
        result = 31 * result + (discountType != null ? discountType.hashCode() : 0);
        return result;
    }
}
