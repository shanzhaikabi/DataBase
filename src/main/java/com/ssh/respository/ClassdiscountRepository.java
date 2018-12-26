package com.ssh.respository;

import com.ssh.entity.Classdiscount;
import com.ssh.entity.Clazz;

import java.util.List;

public interface ClassdiscountRepository extends DomainRepository<Classdiscount,Integer> {
    List<Clazz> getClassByDiscount(String discountType);
}
