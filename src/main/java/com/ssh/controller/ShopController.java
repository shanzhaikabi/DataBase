package com.ssh.controller;

import com.ssh.entity.Clazz;
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

    @RequestMapping(value = "/shop_product",method = RequestMethod.GET)
    public ModelAndView showMyShopProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        if (shop == null){//访客页面
            return new ModelAndView("forward:search.sp",map);
        }
        //管理页面
        List<Object[]> productList = shopService.showProductAndClazzByShopId(shopId);
        List<Clazz> clazzList = shopService.getAll();
        //TODO:加入关于商品和优惠券的修改页面
        return new ModelAndView("shopproduct",map);
    }

    @RequestMapping(value = "/editproduct",method = RequestMethod.GET)
    public ModelAndView editProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId,String productId){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        Product product = productService.get(productId);
        if (shop == null || (product != null && !product.getShopId().equals(shopId))){//访客页面
            return new ModelAndView("forward:search.sp",map);
        }
        if (product == null){
            product = new Product();
            product.setShopId(shopId);
        }

        //TODO:加入关于商品和优惠券的修改页面
        return new ModelAndView("editproduct",map);
    }

    @RequestMapping(value = "/editdiscount",method = RequestMethod.GET)
    public ModelAndView showMyShopProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId,Integer discountType){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        Discount discount = discountService.get(discountType);
        if (shop == null){//访客页面
            return new ModelAndView("forward:search.sp",map);
        }
        if (discount == null){
            discount = new Discount();
        }
        //TODO:加入关于商品和优惠券的修改页面
        return new ModelAndView("editproduct",map);
    }

    @RequestMapping(value = "/shop_discount",method = RequestMethod.GET)
    public ModelAndView showMyShopDiscount(@CookieValue(value = "shopId",defaultValue = "") String shopId){
        ModelMap map=new ModelMap();
        Shop shop = shopService.getShopById(shopId);
        if (shop == null){//访客页面
            return new ModelAndView("forward:search.sp",map);
        }
        //管理页面
        List<Discount> discountList = discountService.getDiscountForShop(shopId);
        //TODO:加入关于商品和优惠券的修改页面
        return new ModelAndView("shopdiscount",map);
    }

    @RequestMapping(value = "/slogin",method = RequestMethod.GET)
    public ModelAndView becomeCustomer(HttpServletResponse response, String id){
        response.addCookie(new Cookie("shopId",id));
        return showMyShopProduct(id);
    }
}
