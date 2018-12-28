package com.ssh.service;

import com.ssh.entity.Discount;
import com.ssh.entity.Shop;
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
    public void addDiscountForProduct(String productId,Integer least, Integer price) {
        Discount discount = new Discount();
        discount.setDiscountLeast(least);
        discount.setDiscountPrice(price);
        discount.setDiscountRule("product");
        Integer discountType = discountService.discountRepository.save(discount);
        discountService.addDiscountByProduct(discountType,productId);
    }
}
