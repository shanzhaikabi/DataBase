package com.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shop {
    private String shopId;
    private String shopName;
    private String shopAddress;
    private String shopTel;
    private String ownerName;
    private String ownerId;
    private Integer shopLevel;
    private String shopStatus;

    @Id
    @Column(name = "shopId", nullable = false, length = 20)
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "shopName", nullable = true, length = 20)
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Basic
    @Column(name = "shopAddress", nullable = true, length = 40)
    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    @Basic
    @Column(name = "shopTel", nullable = true, length = 20)
    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    @Basic
    @Column(name = "ownerName", nullable = true, length = 20)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "ownerId", nullable = true, length = 20)
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "shopLevel", nullable = true)
    public Integer getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(Integer shopLevel) {
        this.shopLevel = shopLevel;
    }

    @Basic
    @Column(name = "shopStatus", nullable = true, length = 10)
    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (shopId != null ? !shopId.equals(shop.shopId) : shop.shopId != null) return false;
        if (shopName != null ? !shopName.equals(shop.shopName) : shop.shopName != null) return false;
        if (shopAddress != null ? !shopAddress.equals(shop.shopAddress) : shop.shopAddress != null) return false;
        if (shopTel != null ? !shopTel.equals(shop.shopTel) : shop.shopTel != null) return false;
        if (ownerName != null ? !ownerName.equals(shop.ownerName) : shop.ownerName != null) return false;
        if (ownerId != null ? !ownerId.equals(shop.ownerId) : shop.ownerId != null) return false;
        if (shopLevel != null ? !shopLevel.equals(shop.shopLevel) : shop.shopLevel != null) return false;
        if (shopStatus != null ? !shopStatus.equals(shop.shopStatus) : shop.shopStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopId != null ? shopId.hashCode() : 0;
        result = 31 * result + (shopName != null ? shopName.hashCode() : 0);
        result = 31 * result + (shopAddress != null ? shopAddress.hashCode() : 0);
        result = 31 * result + (shopTel != null ? shopTel.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (shopLevel != null ? shopLevel.hashCode() : 0);
        result = 31 * result + (shopStatus != null ? shopStatus.hashCode() : 0);
        return result;
    }
}
