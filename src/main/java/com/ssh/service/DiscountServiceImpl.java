package com.ssh.service;

import com.ssh.entity.*;
import com.ssh.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    public Discount get(int id) {
        return discountRepository.get(id);
    }

    @Override
    public Discountdetail doCustomerHaveDiscount(String customerId, int discountType) {
        Discountdetail discountdetail = discountdetailRepository.getDiscountByTypeAndCustomer(discountType,customerId);
        return discountdetail;
    }

    public List<Discountdetail> getAvailableDiscountdetailFromUser(String customerId) {
        List<Discountdetail> list = discountdetailRepository.getAvailableDiscountdetailByCustomerId(customerId);
        return list;
    }

    public List<Discountdetail> getUsedDiscountdetailFromUser(String customerId) {
        List<Discountdetail> list = discountdetailRepository.getUsedDiscountdetailByCustomerId(customerId);
        return list;
    }

    @Override
    public List<Object[]> getAvailableDiscountAndDetailFromUser(String customerId) {
        return discountdetailRepository.getAvailableDiscountAndDetailByCustomerId(customerId);
    }

    @Override
    public List<Object[]> getUsedDiscountAndDetailFromUser(String customerId) {
        return discountdetailRepository.getUsedDiscountAndDetailByCustomerId(customerId);
    }

    public List<Discount> getCurrentDiscountFromUser(String customerId) {
        List<Discountdetail> dis = discountdetailRepository.getAvailableDiscountdetailByCustomerId(customerId);
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

    public List<Product> getProductByDiscount(int discountType){
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
        return list;
    }

    @Override
    public List<Clazz> getClassFromDiscount(int discountType) {
        return classdiscountRepository.getClassByDiscount(discountType);
    }

    @Override
    public List<Product> getProductFromDiscount(int discountType) {
        return productdiscountRepository.getProductByDiscount(discountType);
    }

    @Override
    public List<Shop> getShopFromDiscount(int discountType) {
        return shopdiscountRepository.getShopByDiscount(discountType);
    }

    @Override
    public boolean addDiscountToUser(int discountType, String customerId) {
        Discountdetail discountdetail = new Discountdetail();
        if(discountdetailRepository.getDiscountByTypeAndCustomer(discountType,customerId) != null) return false;
        discountdetail.setCustomerId(customerId);
        discountdetail.setDiscountStatus("yes");
        discountdetail.setDiscountType(discountType);
        discountdetail.setDiscountDate(new Timestamp(new Date().getTime()));
        discountdetailRepository.save(discountdetail);
        return true;
    }

    public boolean addDiscountByShop(Discount discount,String shopId) {
        int discountType = discountRepository.save(discount);
        Shopdiscount sd = new Shopdiscount();
        sd.setDiscountType(discountType);
        sd.setShopId(shopId);
        shopdiscountRepository.save(sd);
        return true;
    }

    @Override
    public void useDiscount(List<Discount> discountList, String customerId,Integer orderId){
        discountdetailRepository.useDiscount(discountList,customerId,orderId);
    }

    @Override
    public void returnDiscount(String customerId, Integer orderId){
        discountdetailRepository.returnDiscount(customerId,orderId);
    }
}
