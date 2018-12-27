package com.ssh.service;

import java.util.List;

public interface CartService {
    String addCart(String customerId,String productId,int quantity);
    List<Object[]> showCart(String customerId);
    String editCart(String customerId,String productId,int quantity);
    void cleanCart(String customerId);

    /**
     * @param customerId
     * @return Object.get(0) means TotalCost , Object.get(1) means TotalDiscount
     * Object.get(2) is a List<Discount> that customer will use
     */
    List<Object> calculateCart(String customerId);
}
