package com.ssh.respository;

import com.ssh.entity.Classdiscount;
import com.ssh.entity.Product;

import java.util.List;

public interface ClassdiscountRepository extends DomainRepository<Classdiscount,Integer> {
    List<Product> getClassProductByDiscount(int discountType);
}
