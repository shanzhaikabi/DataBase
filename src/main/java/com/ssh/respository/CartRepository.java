package com.ssh.respository;

import com.ssh.entity.Cart;

import java.util.List;

public interface CartRepository extends DomainRepository<Cart,Integer>{
    Cart getCartByCustomerIdAndProductId(String customerId,String productId);

    /**
     * @param customerId
     * @return Object[0] is Product when Object[1] is Cart
     */
    List<Object[]> getCartByCustomerId(String customerId);
}
