package com.ssh.service;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> showShopByName(String name);
    String getNameById(String id);
    Shop getShopById(String id);
    void addDiscountForShop(String id,Integer least,Integer price);
    void addDiscountForProducts(String[] id,Integer least,Integer price);
    List<Clazz> getAll();
    List<Object[]> showProductAndClazzByShopId(String shopId);
}
