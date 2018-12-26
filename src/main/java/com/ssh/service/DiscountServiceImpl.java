package com.ssh.service;

import com.ssh.entity.*;
import com.ssh.respository.*;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService{

    @Autowired
    DiscountRepositoryImpl discountRepository;

    @Autowired
    DiscountdetailRepositoryImpl discountdetailRepository;

    @Autowired
    ClassdiscountRepositoryImpl classdiscountRepository;

    @Autowired
    ProductdiscountRepositoryImpl productdiscountRepository;

    @Autowired
    ShopdiscountRepositoryImpl shopdiscountRepository;

    @Override
    public Discount get(String id) {
        return discountRepository.get(id);
    }

    @Override
    public Discountdetail doCustomerHaveDiscount(String customerId, String discountType) {
        return discountdetailRepository.getDiscountByTypeAndCustomer(discountType,customerId);
    }

    public List<Discountdetail> getCurrentDiscountdetailFromUser(String customerId) {
        return discountdetailRepository.getDiscountDetailByCustomerId(customerId);
    }

    public List<Discount> getCurrentDiscountFromUser(String customerId) {
        List<Discountdetail> dis = getCurrentDiscountdetailFromUser(customerId);
        List<Discount> list = dis.stream().map(user -> discountRepository.get(user.getDiscountType())).collect(Collectors.toList());
        return list;
    }

    public List<Discount> getDiscountForProduct(String productId) {
        List<Discount> list = discountRepository.getAllForProduct(productId);
        return list == null ? new ArrayList<>() : list;
    }

    public List<Discount> getDiscountForShop(String shopId) {
        List<Discount> list = discountRepository.getAllForShop(shopId);
        return list == null ? new ArrayList<>() : list;
    }

    public List<Discount> getDiscountForClass(String classId) {
        List<Discount> list = discountRepository.getAllForClass(classId);
        return list == null ? new ArrayList<>() : list;
    }

    public List<Product> getProductByDiscount(String discountType){
        Transaction tx = discountRepository.getCurrentSession().beginTransaction();
        Discount discount = discountRepository.get(discountType);
        List<Product> list = new ArrayList<>();
        if (discount != null) {
            if (discount.getDiscountRule().equals("product")) {
                list = productdiscountRepository.getProductByDiscount(discountType);
            } else if (discount.getDiscountRule().equals("shop")) {
                list = shopdiscountRepository.getShopProductByDiscount(discountType);
            } else if (discount.getDiscountRule().equals("class")) {
                list = classdiscountRepository.getClassProductByDiscount(discountType);
            }
        }
        tx.commit();
        return list;
    }
}
