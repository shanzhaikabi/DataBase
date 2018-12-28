package com.ssh.respository;

import com.ssh.entity.Ordermaster;

import java.util.List;

public interface OrdermasterRepository extends DomainRepository<Ordermaster,Integer>{
    List<Ordermaster> showMyOrder(String customerId);
}
