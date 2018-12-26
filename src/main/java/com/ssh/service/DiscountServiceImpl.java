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
        Discountdetail discountdetail = discountdetailRepository.getDiscountByTypeAndCustomer(discountType,customerId);
        return discountdetail;
    }

    public List<Discountdetail> getCurrentDiscountdetailFromUser(String customerId) {
        List<Discountdetail> list = discountdetailRepository.getDiscountDetailByCustomerId(customerId);
        return list;
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
        Discount discount = discountRepository.get(discountType);
        List<Product> list = new ArrayList<>();
        if (discount != null) {
            if (discount.getDiscountRule().equals("product")) {
                //tx = productdiscountRepository.getCurrentSession().beginTransaction();
                list = productdiscountRepository.getProductByDiscount(discountType);
                //tx.commit();
            } else if (discount.getDiscountRule().equals("shop")) {
                //tx = shopdiscountRepository.getCurrentSession().beginTransaction();
                list = shopdiscountRepository.getShopProductByDiscount(discountType);
                //tx.commit();
            } else if (discount.getDiscountRule().equals("class")) {
                //tx = classdiscountRepository.getCurrentSession().beginTransaction();
                list = classdiscountRepository.getClassProductByDiscount(discountType);
                //tx.commit();
            }
        }
        return list;
    }
}
