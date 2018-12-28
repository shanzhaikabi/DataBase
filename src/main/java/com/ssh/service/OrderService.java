package com.ssh.service;

import java.util.List;

public interface OrderService {
    Integer createOrder(String customerId,List<Object[]> list,int tot);
    void changeStatus(Integer id,String status);
    List<Object> getOrderdetail(Integer id);
}
