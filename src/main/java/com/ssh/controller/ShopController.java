package com.ssh.controller;

import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.service.DiscountServiceImpl;
import com.ssh.service.ProductServiceImpl;
import com.ssh.service.ShopServiceImpl;
import com.ssh.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    DiscountServiceImpl discountService;

    @RequestMapping(value = "/shop",method = RequestMethod.GET)
    public ModelAndView showMyShop(@CookieValue(value = "shopId",defaultValue = "") String shopId, String id){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(id);
        List<Product> productList = productService.showProductByShopId(shopId);
        List<Discount> discountList = discountService.getDiscountForShop(shopId);
        if (shop.getShopId().equals(shopId)){//管理页面
            //TODO:加入关于商品和优惠券的修改页面
        }
        else{//访客页面
            return new ModelAndView("forward:search.sp",map);
        }
        return new ModelAndView("/myshop",map);
    }

    @RequestMapping(value = "/slogin",method = RequestMethod.GET)
    public ModelAndView becomeCustomer(HttpServletResponse response, String id){
        response.addCookie(new Cookie("shopId",id));
        return showMyShop(id,id);
    }
}
