package com.ssh.respository;

import com.ssh.entity.Discount;

import java.util.List;

public interface DiscountRepository extends DomainRepository<Discount,Integer> {
    List<Discount> getAllForShop(String shopId);
    List<Discount> getAllForClass(String classId);
    List<Discount> getAllForProduct(String productId);
}
