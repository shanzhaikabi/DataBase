package com.ssh.service;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;

import java.util.List;

public interface DiscountService {
    List<Discount> getCurrentDiscountFromUser(String customerId);
    List<Discount> getDiscountForProduct(String productId);
    List<Discount> getDiscountForShop(String shopId);
    List<Discount> getDiscountForClass(String classId);
    List<Shop> getShopByDiscount(String discountType);
    List<Clazz> getClazzByDiscount(String discountType);
    List<Product> getProductByDiscount(String discountType);
}
