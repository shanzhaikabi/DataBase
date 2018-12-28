package com.ssh.respository;

import com.ssh.entity.Orderdetail;

import java.util.List;

public interface OrderdetailRepository extends DomainRepository<Orderdetail,Integer> {
    List<Object[]> getOrderdetailByOrderId(Integer orderId);
}
