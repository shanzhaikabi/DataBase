package com.ssh.service;

import com.ssh.entity.Shop;
import com.ssh.respository.ShopRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopRepositoryImpl shopRepository;

    public List<Shop> ShowShopByName(String name) {
        List shopList = shopRepository.findByName(name);
        return shopList;
    }

    public String GetNameById(String id) {
        Shop shop = shopRepository.get(id);
        return shop == null ? null : shop.getShopName();
    }
}
