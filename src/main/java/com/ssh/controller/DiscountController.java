package com.ssh.controller;

import com.ssh.entity.*;
import com.ssh.respository.ProductRepositoryImpl;
import com.ssh.service.CustomerServiceImpl;
import com.ssh.service.DiscountServiceImpl;
import com.ssh.service.ProductServiceImpl;
import com.ssh.service.ShopServiceImpl;
import com.ssh.utils.DiscountUtils;
import com.ssh.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class DiscountController {
    @Autowired
    DiscountServiceImpl discountService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    ShopServiceImpl shopService;

    @RequestMapping(value = "/discount",method = RequestMethod.GET)
    public ModelAndView showDiscountDetail(@CookieValue(value = "customerId",defaultValue = "") String customerId, Integer id){
        ModelMap map=new ModelMap();
        Discount discount = discountService.get(id);
        if (discount == null) return new ModelAndView("404 NOT FOUND");
        Customer customer = customerService.get(customerId);
        String status = "";
        if (customer == null) status = "login";
        else{
            Discountdetail discountdetail = discountService.doCustomerHaveDiscount(customerId,id);
            if (discountdetail == null) status = "no";
            else status = "yes";
        }
        List<Product> list = discountService.getProductByDiscount(id);
        map.put("result", SearchUtils.search_result_product(list));
        map.put("discountresult", DiscountUtils.discount_detail(discount,status));
        return new ModelAndView("/discount",map);
    }

    @RequestMapping(value = "/receivediscount",method = RequestMethod.GET)
    public ModelAndView addDiscount(@CookieValue(value = "customerId",defaultValue = "") String customerId, Integer id){
        ModelMap map = new ModelMap();
        if (id == null){
            map.put("result","错误的请求");
        }
        else if (discountService.addDiscountToUser(id,customerId)){
            map.put("result","领取成功");
            map.put("discount",DiscountUtils.discount_detail(discountService.get(id),""));
        }
        else{
            map.put("result","领取失败");
        }
        return new ModelAndView("/receivediscount",map);
    }

    @RequestMapping(value = "/editdiscount.do",method = RequestMethod.POST)
    public ModelAndView editProduct(@CookieValue(value = "shopId",defaultValue = "") String shopId, Discount discount,String[] productId){
        Shop shop = shopService.getShopById(shopId);
        if (shop == null){//访客页面
            return new ModelAndView("forward:search");
        }
        ModelMap map=new ModelMap();
        if (discount.getDiscountType() == null){
            if (discount.getDiscountRule() == "shop"){
                shopService.addDiscountForShop(shopId,discount.getDiscountLeast(),discount.getDiscountPrice());
            }
            else if (discount.getDiscountRule() == "product"){
                shopService.addDiscountForProduct(productId,discount.getDiscountLeast(),discount.getDiscountPrice());
            }
        }
        else discountService.saveOrUpdate(discount);
        return new ModelAndView("redirect:shop_discount");
    }
}
