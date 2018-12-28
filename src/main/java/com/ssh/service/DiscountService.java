package com.ssh.service;

import com.ssh.entity.*;

import java.util.List;

public interface DiscountService {
    Discount get(int id);
    Discountdetail doCustomerHaveDiscount(String customerId,int discountType);
    List<Discountdetail> getAvailableDiscountdetailFromUser(String customerId);
    List<Discountdetail> getUsedDiscountdetailFromUser(String customerId);
    List<Object[]> getAvailableDiscountAndDetailFromUser(String customerId);
    List<Object[]> getUsedDiscountAndDetailFromUser(String customerId);
    List<Object[]> getDiscountAndDetailForShop(String shopId);
    List<Discount> getCurrentDiscountFromUser(String customerId);
    List<Discount> getDiscountForProduct(String productId);
    List<Discount> getDiscountForShop(String shopId);
    List<Discount> getDiscountForClass(String classId);
    List<Product> getProductByDiscount(int discountType);
    List<Clazz> getClassFromDiscount(int discountType);
    List<Product> getProductFromDiscount(int discountType);
    List<Shop> getShopFromDiscount(int discountType);
    List<Discount> getDiscountUsedInOrder(Integer orderId);
    boolean addDiscountToUser(Integer discountType,String customerId);
    void useDiscount(List<Discount> discountList,String customerId,Integer orderId);
    void returnDiscount(String customerId, Integer orderId);
    boolean addDiscountByShop(Integer discountType,String shopId);
    boolean addDiscountByProducts(Integer discountType,String[] productId);
    void saveOrUpdate(Discount discount);
}
