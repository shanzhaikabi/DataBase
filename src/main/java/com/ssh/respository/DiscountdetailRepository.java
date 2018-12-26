package com.ssh.respository;

import com.ssh.entity.Discountdetail;

import java.util.List;

public interface DiscountdetailRepository extends DomainRepository<Discountdetail, Integer>{
    List<Discountdetail> getDiscountdetailByCustomerId(String customerId);
    List<Discountdetail> getUsedDiscountdetailByCustomerId(String customerId);
    List<Discountdetail> getAvailableDiscountdetailByCustomerId(String customerId);
    List<Object[]> getUsedDiscountAndDetailByCustomerId(String customerId);
    List<Object[]> getAvailableDiscountAndDetailByCustomerId(String customerId);
    Discountdetail getDiscountByTypeAndCustomer(int discountType,String customerId);
}
