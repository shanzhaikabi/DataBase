package com.ssh.service;

import com.ssh.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> getCurrentDiscountFromUser(String customerId);
    List<Discount> getDiscountForProduct(String productId);
    List<Discount> getDiscountForShop(String shopId);
    List<Discount> getDiscountForClass(String classId);
}
