package com.ssh.respository;

import com.ssh.entity.Discount;
import com.ssh.entity.Discountdetail;

import java.util.List;

public interface DiscountdetailRepository extends DomainRepository<Discountdetail, Integer>{
    List<Discountdetail> getDiscountDetailByCustomerId(String customerId);
}
