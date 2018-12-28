package com.ssh.service;

import com.ssh.entity.*;
import com.ssh.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
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

    @Override
    public List<Object[]> getDiscountAndDetailForShop(String shopId) {
        List<Object[]> list = productdiscountRepository.getDiscountAndDetailForShop(shopId);
        Map<Discount,List<Product>> tmpMap = new HashMap<>();
        for (Object[] objects : list) {
            Discount discount = (Discount) objects[0];
            if (!tmpMap.containsKey(discount)){
                tmpMap.put(discount,new ArrayList<>());
            }
            tmpMap.get(discount).add((Product) objects[1]);
        }
        List<Object[]> ret = new ArrayList<>();
        tmpMap.forEach((discount, productList) -> ret.add(new Object[]{discount,productList}));
        return ret;
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
    public List<Discount> getDiscountUsedInOrder(Integer orderId) {
        return discountdetailRepository.getDiscountUsedInOrder(orderId).stream().map(objects -> (Discount)objects[0]).collect(Collectors.toList());
    }

    @Override
    public boolean addDiscountToUser(Integer discountType, String customerId) {
        Discountdetail discountdetail = new Discountdetail();
        if(discountdetailRepository.getDiscountByTypeAndCustomer(discountType,customerId) != null) return false;
        discountdetail.setCustomerId(customerId);
        discountdetail.setDiscountStatus("yes");
        discountdetail.setDiscountType(discountType);
        discountdetail.setDiscountDate(new Timestamp(new Date().getTime()));
        discountdetailRepository.save(discountdetail);
        return true;
    }

    public boolean addDiscountByShop(Integer discountType,String shopId) {
        Shopdiscount sd = new Shopdiscount();
        sd.setDiscountType(discountType);
        sd.setShopId(shopId);
        shopdiscountRepository.save(sd);
        return true;
    }

    public boolean removeDiscountProducts(Integer discountType, String[] productId) {
        List<String> strings = new ArrayList<>();
        for (String s : productId) {
            strings.add(s);
        }
        List<Productdiscount> list = productdiscountRepository.removeDiscountProducts(discountType);
        list.forEach(
                productdiscount -> {
                    if (strings.contains(productdiscount.getProductId())){
                        strings.remove(productdiscount.getProductId());
                    }
                    else{
                        productdiscountRepository.delete(productdiscount);
                    }
                }
        );
        String[] ret = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            ret[i] = strings.get(i);
        }
        addDiscountByProducts(discountType,ret);
        return true;
    }

    @Override
    public boolean addDiscountByProducts(Integer discountType, String[] productId) {
        for (String s : productId) {
            Productdiscount pd = new Productdiscount();
            System.out.println("wtf " + s);
            pd.setDiscountType(discountType);
            pd.setProductId(s.replace(" ",""));
            productdiscountRepository.save(pd);
        }
        return true;
    }

    @Override
    public void saveOrUpdate(Discount discount) {
        discountRepository.saveOrUpdate(discount);
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
