package com.ssh.service;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.respository.ProductRepositoryImpl;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepositoryImpl productRepository;
    @Autowired
    private ShopServiceImpl shopService;
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private DiscountServiceImpl discountService;

    public List ShowProductDetail(String id){
        //Transaction tx = productRepository.getCurrentSession().beginTransaction();
        List list = new ArrayList();
        Product product = productRepository.get(id);
        list.add(product);
        //tx.commit();
        if (null == product) return null;
        Clazz clazz = classService.GetClassById(product.getClassId());
        if (null == clazz) return null;
        list.add(clazz);
        Shop shop = shopService.GetShopById(product.getShopId());
        if (null == shop) return null;
        list.add(shop);
        List<Discount> discountList = new ArrayList<>();
        discountList.addAll(discountService.getDiscountForShop(shop.getShopId()));
        discountList.addAll(discountService.getDiscountForClass(clazz.getClassId()));
        discountList.addAll(discountService.getDiscountForProduct(product.getProductId()));
        System.out.println(discountList);
        list.add(3,discountList);
        return list;
    }

    public List ShowProductDetailHavingCustomer(String id,String CustomerId){
        List list = ShowProductDetail(id);
        List<Discount> currentDiscountList = discountService.getCurrentDiscountFromUser(CustomerId);
        currentDiscountList.retainAll((List)list.get(3));
        list.add(currentDiscountList);
        return list;
    }

    public List<Product> ShowProductByName(String name) {
        //Transaction tx = productRepository.getCurrentSession().beginTransaction();
        List list = productRepository.findByName(name);
        //tx.commit();
        return list;
    }

    public List<Product> ShowProductByClassId(String classId) {
        //Transaction tx = productRepository.getCurrentSession().beginTransaction();
        List list = productRepository.findByClass(classId);
        //tx.commit();
        return list;
    }

    public List<Product> ShowProductByShopId(String shopId) {
        //Transaction tx = productRepository.getCurrentSession().beginTransaction();
        List list = productRepository.findByShop(shopId);
        //tx.commit();
        return list;
    }
}
