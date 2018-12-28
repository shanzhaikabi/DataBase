package com.ssh.service;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.respository.ClazzRepositoryImpl;
import com.ssh.respository.ShopRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopRepositoryImpl shopRepository;
    @Autowired
    DiscountServiceImpl discountService;
    @Autowired
    ClazzRepositoryImpl clazzRepository;

    public List<Shop> showShopByName(String name) {
        List shopList = shopRepository.findByName(name);
        return shopList;
    }

    public String getNameById(String id) {
        Shop shop = shopRepository.get(id);
        return shop == null ? null : shop.getShopName();
    }

    public Shop getShopById(String id) {
        Shop shop = shopRepository.get(id);
        return shop;
    }

    @Override
    public void addDiscountForShop(String shopId,Integer least, Integer price) {
        Discount discount = new Discount();
        discount.setDiscountLeast(least);
        discount.setDiscountPrice(price);
        discount.setDiscountRule("shop");
        Integer discountType = discountService.discountRepository.save(discount);
        discountService.addDiscountByShop(discountType,shopId);
    }

    @Override
    public void addDiscountForProducts(String[] productId,Integer least, Integer price) {
        Discount discount = new Discount();
        discount.setDiscountLeast(least);
        discount.setDiscountPrice(price);
        discount.setDiscountRule("product");
        Integer discountType = discountService.discountRepository.save(discount);
        discountService.addDiscountByProducts(discountType,productId);
    }

    @Override
    public List<Clazz> getAll() {
        return clazzRepository.findAll();
    }

    public List<Object[]> showProductAndClazzByShopId(String shopId) {
        List<Object[]> list = shopRepository.showProductAndClazz(shopId);
        return list;
    }
}
