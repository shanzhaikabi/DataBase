package com.ssh.service;

import com.ssh.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> ShowShopByName(String name);
    String GetNameById(String id);
}
