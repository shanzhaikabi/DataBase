package com.ssh.controller;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.service.ProductServiceImpl;
import com.ssh.service.ShopServiceImpl;
import com.ssh.utils.DiscountUtils;
import com.ssh.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ShopServiceImpl shopService;

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public ModelAndView showProduct(@CookieValue(value = "customerId",defaultValue = "") String customerId, String id){
        ModelMap map=new ModelMap();
        List targetList = new ArrayList();
        if (customerId.length() == 0) targetList = productService.showProductDetail(id);
        else targetList = productService.showProductDetailHavingCustomer(id,customerId);
        if(targetList == null || targetList.size() < 4){
            return new ModelAndView("404 NOT FOUND");
        }
        else if (targetList.size() == 4){
            targetList.add(new ArrayList());
        }
        map.put("result", ProductUtils.product_detail((Product) targetList.get(0),(Clazz) targetList.get(1),(Shop)targetList.get(2))
        + DiscountUtils.discount_for_product((List<Discount>)targetList.get(3),(List<Discount>)targetList.get(4))
        + ProductUtils.product_into_cart((Product)targetList.get(0)));
        return new ModelAndView("/product",map);
    }

    @RequestMapping(value = "/editproduct.do",method = RequestMethod.POST)
    public ModelAndView editProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId, Product product){
        Shop shop = shopService.getShopById(shopId);
        if (!shop.getShopId().equals(product.getShopId())){//访客页面
            return new ModelAndView("forward:product");
        }
        ModelMap map=new ModelMap();
        if (product.getProductId() == null || product.getProductId().length() == 0 || product.getProductId().equals("null")){//创建
            product.setProductId("" + new Date().getTime());
        }
        if (product.getProductStock() < 0){
            productService.deleteProduct(product);
        }
        else productService.saveOrUpdate(product);
        return new ModelAndView("redirect:shop_product");
    }
}
