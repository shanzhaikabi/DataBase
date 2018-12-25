package com.ssh.service;

import com.ssh.entity.Shop;

import java.util.List;

public interface ShopService {
    /**
     *
     * @param name
     * @return
     */
    public List<Shop> ShowShopByName(String name);
}
