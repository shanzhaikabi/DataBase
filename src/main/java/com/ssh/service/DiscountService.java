package com.ssh.service;

import com.ssh.entity.*;

import java.util.List;

public interface DiscountService {
    Discount get(String id);
    Discountdetail doCustomerHaveDiscount(String customerId,String discountType);
    List<Discountdetail> getAvailableDiscountdetailFromUser(String customerId);
    List<Discountdetail> getUsedDiscountdetailFromUser(String customerId);
    List<Discount> getCurrentDiscountFromUser(String customerId);
    List<Discount> getDiscountForProduct(String productId);
    List<Discount> getDiscountForShop(String shopId);
    List<Discount> getDiscountForClass(String classId);
    List<Product> getProductByDiscount(String discountType);
    boolean addDiscountToUser(String discountType,String customerId);
    boolean addDiscountByShop(Discount discount);
}
