package com.ssh.service;

import com.ssh.entity.Product;

import java.util.List;

public interface OrderService {
    void createOrder(String customerId,List<Object[]> list,int tot);
    void changeStatus(Integer id,String status);
}
