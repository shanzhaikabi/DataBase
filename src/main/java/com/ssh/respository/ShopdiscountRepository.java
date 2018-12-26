package com.ssh.respository;

import com.ssh.entity.Shop;
import com.ssh.entity.Shopdiscount;

import java.util.List;

public interface ShopdiscountRepository extends DomainRepository<Shopdiscount,Integer>{
    List<Shop> getShopByDiscount(String discountType);
}
