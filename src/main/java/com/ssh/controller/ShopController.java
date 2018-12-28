package com.ssh.controller;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.service.DiscountServiceImpl;
import com.ssh.service.ProductServiceImpl;
import com.ssh.service.ShopServiceImpl;
import com.ssh.utils.ShopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    DiscountServiceImpl discountService;

    @RequestMapping(value = "/shop_product",method = RequestMethod.GET)
    public ModelAndView showMyShopProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        if (shop == null){//访客页面
            return new ModelAndView("redirect:search.sp");
        }
        //管理页面
        List<Object[]> productList = shopService.showProductAndClazzByShopId(shopId);
        map.put("result", ShopUtils.shop_product(productList));
        return new ModelAndView("shopproduct",map);
    }

    @RequestMapping(value = "/editproduct",method = RequestMethod.GET)
    public ModelAndView editProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId,String id){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        Product product = productService.get(id);
        List<Clazz> clz = shopService.getAll();
        if (shop == null || (product != null && !product.getShopId().equals(shopId))){//访客页面
            return new ModelAndView("redirect:search.sp",map);
        }
        if (product == null){
            product = new Product();
            product.setShopId(shopId);
        }
        map.put("result",ShopUtils.shop_one_product(product)+ShopUtils.shop_class(clz));
        return new ModelAndView("editproduct",map);
    }

    @RequestMapping(value = "/editdiscount",method = RequestMethod.GET)
    public ModelAndView showMyShopProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId,Integer id){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        if (shop == null){//访客页面
            return new ModelAndView("redirect:search.sp",map);
        }
        List<Product> list = productService.showProductByShopId(shopId);
        Discount discount = new Discount();
        if (id == -1){
            //todo something1
            discount.setDiscountRule("shop");
            discount.setDiscountType(-1);
            map.put("result",ShopUtils.shop_one_discount(discount));
            return new ModelAndView("shopdiscount",map);
        }
        else if (id == -2){
            //do something2
            discount.setDiscountRule("product");
            discount.setDiscountType(-2);
            map.put("result",ShopUtils.shop_one_discount(discount) + ShopUtils.shop_discount_product(list,new ArrayList<>()));
            return new ModelAndView("shopdiscountproduct",map);
        }
        discount = discountService.get(id);
        if (discount.getDiscountRule().equals("shop")){
            //do something1
            discount.setDiscountRule("shop");
            map.put("result",ShopUtils.shop_one_discount(discount));
            return new ModelAndView("shopdiscount",map);
        }
        discount.setDiscountRule("product");
        List<Product> productList = discountService.getProductByDiscount(discount.getDiscountType());
        map.put("result",ShopUtils.shop_one_discount(discount) + ShopUtils.shop_discount_product(list,productList));
        return new ModelAndView("shopdiscountproduct",map);
    }

    @RequestMapping(value = "/shop_discount",method = RequestMethod.GET)
    public ModelAndView showMyShopDiscount(@CookieValue(value = "shopId",defaultValue = "") String shopId){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        if (shop == null){//访客页面
            return new ModelAndView("redirect:search.sp",map);
        }
        //管理页面
        List<Discount> discountList = discountService.getDiscountForShop(shopId);
        List<Object[]> list = discountService.getDiscountAndDetailForShop(shopId);
        //TODO:加入关于商品和优惠券的修改页面
        map.put("result",ShopUtils.shop_discount_shop(discountList) + ShopUtils.shop_discount_set_product(list));
        return new ModelAndView("shopdiscount",map);
    }

    @RequestMapping(value = "/slogin",method = RequestMethod.GET)
    public ModelAndView becomeCustomer(HttpServletResponse response, String id){
        response.addCookie(new Cookie("shopId",id));
        return showMyShopProduct(id);
    }
}
