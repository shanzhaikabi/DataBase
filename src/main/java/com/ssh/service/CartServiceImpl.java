package com.ssh.service;

import com.ssh.entity.Cart;
import com.ssh.respository.CartRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepositoryImpl cartRepository;

    @Override
    public String addCart(String customerId, String productId, int quantity) {
        if (quantity == 0) return "cancel";
        String status = "";
        Cart tmp = cartRepository.getCartByCustomerIdAndProductId(customerId,productId);
        if (tmp == null){
            Cart cart = new Cart();
            cart.setCustomerId(customerId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);
            cart.setStatus(status);
            cartRepository.save(cart);
        }
        else{
            tmp.setQuantity(tmp.getQuantity() + quantity);
            cartRepository.saveOrUpdate(tmp);
        }
        return "success";
    }

    @Override
    public List<Object[]> showCart(String customerId){
        List<Object[]> list = cartRepository.getCartByCustomerId(customerId);
        return list;
    }
}
