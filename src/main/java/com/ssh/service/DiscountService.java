package com.ssh.service;

import com.ssh.entity.*;

import java.util.List;

public interface DiscountService {
    Discount get(int id);
    Discountdetail doCustomerHaveDiscount(String customerId,int discountType);
    List<Discountdetail> getAvailableDiscountdetailFromUser(String customerId);
    List<Discountdetail> getUsedDiscountdetailFromUser(String customerId);

    /**
     * @param customerId
     * @return Object[] where Object[0] is Discount and Object[1] is Discountdetail
     */
    List<Object[]> getAvailableDiscountAndDetailFromUser(String customerId);
    /**
     * @param customerId
     * @return Object[] where Object[0] is Discount and Object[1] is Discountdetail
     */
    List<Object[]> getUsedDiscountAndDetailFromUser(String customerId);
    List<Discount> getCurrentDiscountFromUser(String customerId);
    List<Discount> getDiscountForProduct(String productId);
    List<Discount> getDiscountForShop(String shopId);
    List<Discount> getDiscountForClass(String classId);
    List<Product> getProductByDiscount(int discountType);
    List<Clazz> getClassFromDiscount(int discountType);
    List<Product> getProductFromDiscount(int discountType);
    List<Shop> getShopFromDiscount(int discountType);
    boolean addDiscountToUser(int discountType,String customerId);
    void useDiscount(List<Discount> discountList,String customerId,Integer orderId);
    void returnDiscount(String customerId, Integer orderId);
    boolean addDiscountByShop(Discount discount,String shopId);
}
