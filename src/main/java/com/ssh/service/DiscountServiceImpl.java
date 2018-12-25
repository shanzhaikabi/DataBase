package com.ssh.service;

import com.ssh.entity.Customer;
import com.ssh.entity.Discount;
import com.ssh.entity.Discountdetail;
import com.ssh.respository.DiscountRepositoryImpl;
import com.ssh.respository.DiscountdetailRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService{

    @Autowired
    DiscountRepositoryImpl discountRepository;

    @Autowired
    DiscountdetailRepositoryImpl discountdetailRepository;

    public List<Discount> getCurrentDiscountFromUser(String customerId) {
        List<Discountdetail> dis = discountdetailRepository.getDiscountDetailByCustomerId(customerId);
        List<Discount> list = new ArrayList<>();
        dis.forEach(Discountdetail -> list.add(discountRepository.get(Discountdetail.getDiscountType())));
        return list;
    }

    public List<Discount> getDiscountForProduct(String productId) {
        List<Object[]> list = discountRepository.getAllForProduct(productId);
        return getDiscountList(list);
    }

    public List<Discount> getDiscountForShop(String shopId) {
        List<Object[]> list = discountRepository.getAllForShop(shopId);
        return getDiscountList(list);
    }

    public List<Discount> getDiscountForClass(String classId) {
        List<Object[]> list = discountRepository.getAllForClass(classId);
        return getDiscountList(list);
    }

    private List<Discount> getDiscountList(List list) {
        List<Discount> ret = new ArrayList<>();
        Iterator<Object[]> iterator = list.iterator();
        while(iterator.hasNext()){
            Object[] o = iterator.next();
            ret.add((Discount) o[0]);
        }
        return ret;
    }
}
