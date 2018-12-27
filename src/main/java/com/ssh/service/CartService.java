package com.ssh.service;

import java.util.List;

public interface CartService {
    String addCart(String customerId,String productId,int quantity);
    List<Object[]> showCart(String customerId);
    String editCart(String customerId,String productId,int quantity);
    void cleanCart(String customerId);
}
