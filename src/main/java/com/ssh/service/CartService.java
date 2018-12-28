package com.ssh.service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CartService {
    String addCart(String customerId,String productId,int quantity);
    List<Object[]> showCart(String customerId);
    String editCart(Integer cartId, int quantity);
    void cleanCart(String customerId);
    void changeStatus(String customerId,List<Integer> cartId);

    /**
     * @param customerId
     * @return Object.get(0) means TotalCost , Object.get(1) means TotalDiscount
     * Object.get(2) is a List<Discount> that customer will use
     */
    List<Object> calculateCart(String customerId);
}
