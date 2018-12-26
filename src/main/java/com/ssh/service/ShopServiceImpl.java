package com.ssh.service;

import com.ssh.entity.Shop;
import com.ssh.respository.ShopRepositoryImpl;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopRepositoryImpl shopRepository;

    public List<Shop> ShowShopByName(String name) {
        //Transaction tx = shopRepository.getCurrentSession().beginTransaction();
        List shopList = shopRepository.findByName(name);
        //tx.commit();
        return shopList;
    }

    public String GetNameById(String id) {
        //Transaction tx = shopRepository.getCurrentSession().beginTransaction();
        Shop shop = shopRepository.get(id);
        //tx.commit();
        return shop == null ? null : shop.getShopName();
    }

    public Shop GetShopById(String id) {
        //Transaction tx = shopRepository.getCurrentSession().beginTransaction();
        Shop shop = shopRepository.get(id);
        //tx.commit();
        return shop;
    }


}
