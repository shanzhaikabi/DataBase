package com.ssh.respository;

import com.ssh.entity.Product;
import com.ssh.entity.Shopdiscount;

import java.util.List;

public interface ShopdiscountRepository extends DomainRepository<Shopdiscount,Integer>{
    List<Product> getShopProductByDiscount(int discountType);
}
