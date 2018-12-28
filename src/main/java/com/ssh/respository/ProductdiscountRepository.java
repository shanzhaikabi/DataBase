package com.ssh.respository;

import com.ssh.entity.Product;
import com.ssh.entity.Productdiscount;

import java.util.List;

public interface ProductdiscountRepository extends DomainRepository<Productdiscount,Integer>{
    List<Product> getProductByDiscount(int discountType);
    List<Object[]> getDiscountAndDetailForShop(String shopId);
}
