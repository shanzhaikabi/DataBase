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
    boolean addDiscountToUser(int discountType,String customerId);
    boolean addDiscountByShop(Discount discount);
}
