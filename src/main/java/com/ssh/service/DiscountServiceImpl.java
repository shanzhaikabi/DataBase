package com.ssh.service;

import com.ssh.entity.Customer;
import com.ssh.entity.Discount;
import com.ssh.entity.Discountdetail;
import com.ssh.respository.DiscountRepositoryImpl;
import com.ssh.respository.DiscountdetailRepositoryImpl;
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

    public List<Discount> getCurrentDiscountFromUser(String customerId) {
        List<Discountdetail> dis = discountdetailRepository.getDiscountDetailByCustomerId(customerId);
        List<Discount> list = new ArrayList<Discount>();
        dis.forEach((Discountdetail) -> list.add(discountRepository.get(Discountdetail.getDiscountType())));
        return list;
    }

    public List<Discount> getDiscountForProduct(String productId) {
        return discountRepository.getAllForProduct(productId);
    }

    public List<Discount> getDiscountForShop(String shopId) {
        return discountRepository.getAllForShop(shopId);
    }

    public List<Discount> getDiscountForClass(String classId) {
        return discountRepository.getAllForClass(classId);
    }
}
