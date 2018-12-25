package com.ssh.respository;

import com.ssh.entity.Shop;

import java.util.List;

public interface ShopRepository extends DomainRepository<Shop,String>{
    List<Shop> findByName(String name);
}
