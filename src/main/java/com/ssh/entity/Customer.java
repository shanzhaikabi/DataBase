package com.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    private String customerId;
    private String customerName;
    private String customerIdentity;
    private String customerAddress;
    private String customerTel;
    private Integer customerLevel;

    @Id
    @Column(name = "customerId", nullable = false, length = 20)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customerName", nullable = true, length = 20)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "customerIdentity", nullable = true, length = 20)
    public String getCustomerIdentity() {
        return customerIdentity;
    }

    public void setCustomerIdentity(String customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    @Basic
    @Column(name = "customerAddress", nullable = true, length = 40)
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Basic
    @Column(name = "customerTel", nullable = true, length = 20)
    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    @Basic
    @Column(name = "customerLevel", nullable = true)
    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != null ? !customerId.equals(customer.customerId) : customer.customerId != null) return false;
        if (customerName != null ? !customerName.equals(customer.customerName) : customer.customerName != null)
            return false;
        if (customerIdentity != null ? !customerIdentity.equals(customer.customerIdentity) : customer.customerIdentity != null)
            return false;
        if (customerAddress != null ? !customerAddress.equals(customer.customerAddress) : customer.customerAddress != null)
            return false;
        if (customerTel != null ? !customerTel.equals(customer.customerTel) : customer.customerTel != null)
            return false;
        if (customerLevel != null ? !customerLevel.equals(customer.customerLevel) : customer.customerLevel != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (customerIdentity != null ? customerIdentity.hashCode() : 0);
        result = 31 * result + (customerAddress != null ? customerAddress.hashCode() : 0);
        result = 31 * result + (customerTel != null ? customerTel.hashCode() : 0);
        result = 31 * result + (customerLevel != null ? customerLevel.hashCode() : 0);
        return result;
    }
}
