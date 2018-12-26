package com.ssh.service;

import com.ssh.entity.*;
import com.ssh.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Discount> getCurrentDiscountFromUser(String customerId) {
        List<Discountdetail> dis = discountdetailRepository.getDiscountDetailByCustomerId(customerId);
        List<Discount> list = new ArrayList<>();
        dis.forEach(Discountdetail -> list.add(discountRepository.get(Discountdetail.getDiscountType())));
        return list;
    }

    public List<Discount> getDiscountForProduct(String productId) {
        List<Discount> list = discountRepository.getAllForProduct(productId);
        return list;
    }

    public List<Discount> getDiscountForShop(String shopId) {
        List<Discount> list = discountRepository.getAllForShop(shopId);
        return list;
    }

    public List<Discount> getDiscountForClass(String classId) {
        List<Discount> list = discountRepository.getAllForClass(classId);
        return list;
    }

    public List<Clazz> getClazzByDiscount(String discountType){
        List<Clazz> list = classdiscountRepository.getClassByDiscount(discountType);
        return list;
    }

    public List<Shop> getShopByDiscount(String discountType){
        List<Shop> list = shopdiscountRepository.getShopByDiscount(discountType);
        return list == null ? new ArrayList<>() : list;
    }

    public List<Product> getProductByDiscount(String discountType){
        List<Product> list = productdiscountRepository.getProductByDiscount(discountType);
        return list == null ? new ArrayList<>() : list;
    }
}
