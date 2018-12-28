package com.ssh.service;

import com.ssh.entity.Ordermaster;

import java.util.List;

public interface OrderService {
    Integer createOrder(String customerId,List<Object[]> list,int tot);
    void changeStatus(Integer id,String status);
    List<Object> getOrderdetail(Integer id);
    List<Ordermaster> showMyOrder(String customerId);
    Ordermaster getOrdermaster(Integer id);
}
