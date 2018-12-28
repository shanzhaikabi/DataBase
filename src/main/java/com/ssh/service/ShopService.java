package com.ssh.service;

import com.ssh.entity.Product;
import com.ssh.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> showShopByName(String name);
    String getNameById(String id);
    Shop getShopById(String id);
}
