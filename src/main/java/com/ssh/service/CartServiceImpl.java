package com.ssh.service;

import com.ssh.entity.Cart;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.respository.CartRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepositoryImpl cartRepository;
    @Autowired
    DiscountServiceImpl discountService;

    @Override
    public String addCart(String customerId, String productId, int quantity) {
        if (quantity == 0) return "cancel";
        String status = "yes";
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

    @Override
    public String editCart(Integer cartId, int quantity) {
        Cart cart = cartRepository.get(cartId);
        if (cart == null) return "cart not exist";
        if (quantity == 0){
            cartRepository.delete(cart);
            return "delete";
        }
        cart.setQuantity(quantity);
        cartRepository.saveOrUpdate(cart);
        return "edit";
    }

    @Override
    public void cleanCart(String customerId) {
        showCart(customerId).forEach(objects -> cartRepository.delete((Cart) objects[1]));
    }

    @Override
    public void changeStatus(String customerId,List<Integer> cartId) {
        cartRepository.getCartByCustomerId(customerId).forEach(objects ->
        {
            Cart cart = (Cart) objects[1];
            if (cartId.contains(cart.getId())){
                cart.setStatus("yes");
            }
            else cart.setStatus("no");
            cartRepository.saveOrUpdate(cart);
        });
    }

    @Override
    public List<Object> calculateCart(String customerId) {
        List<Object[]> productList = cartRepository.getCartByCustomerId(customerId);
        productList.removeIf(objects -> !((Cart)objects[1]).getStatus().equals("yes"));
        List<Discount> discountList = discountService.getAvailableDiscountAndDetailFromUser(customerId)
                .stream().map(objects -> (Discount)objects[0]).collect(Collectors.toList());
        List<Discount> useDiscountList = new ArrayList<>();
        Map<String,Integer> clazzTot = new HashMap<>();
        Map<String,Integer> productTot = new HashMap<>();
        Map<String,Integer> shopTot = new HashMap<>();
        int tot = 0,dis = 0;
        Iterator iterator = productList.iterator();
        while(iterator.hasNext()){//统计商品
            Object[] objects = (Object[]) iterator.next();
            Product product = (Product) objects[0];
            Cart cart = (Cart)objects[1];
            int price = product.getProductPrice() * cart.getQuantity();
            clazzTot.put(product.getClassId(),price + clazzTot.getOrDefault(product.getClassId(),0));
            productTot.put(product.getProductId(),price + productTot.getOrDefault(product.getProductId(),0));
            shopTot.put(product.getShopId(),price + shopTot.getOrDefault(product.getShopId(),0));
            tot = tot + price;
        };
        iterator = discountList.iterator();
        while(iterator.hasNext()){//统计优惠券
            Discount discount = (Discount) iterator.next();
            if (discount.getDiscountRule().equals("class")){
                discountService.getClassFromDiscount(discount.getDiscountType()).forEach(clazz -> {
                    if (clazzTot.containsKey(clazz.getClassId()) && discount.getDiscountLeast() <= clazzTot.get(clazz.getClassId()))
                        useDiscountList.add(discount);
                });
            }
            else if (discount.getDiscountRule().equals("shop")){
                discountService.getShopFromDiscount(discount.getDiscountType()).forEach(clazz -> {
                    if (shopTot.containsKey(clazz.getShopId()) && discount.getDiscountLeast() <= shopTot.get(clazz.getShopId()))
                        useDiscountList.add(discount);
                });
            }
            else if (discount.getDiscountRule().equals("product")){
                discountService.getProductFromDiscount(discount.getDiscountType()).forEach(clazz -> {
                    if (productTot.containsKey(clazz.getProductId()) && discount.getDiscountLeast() <= productTot.get(clazz.getProductId()))
                        useDiscountList.add(discount);
                });
            }
        }
        iterator = useDiscountList.iterator();
        while(iterator.hasNext()) {//统计优惠券
            Discount discount = (Discount) iterator.next();
            dis = dis + discount.getDiscountPrice();
        }
        if (dis > tot) dis = tot;
        List ret = new ArrayList();
        ret.add(tot);
        ret.add(dis);
        ret.add(useDiscountList);
        ret.add(productList);
        return ret;
    }
}
