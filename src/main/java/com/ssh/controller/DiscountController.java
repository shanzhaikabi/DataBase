package com.ssh.controller;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.service.DiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DiscountController {
    @Autowired
    DiscountServiceImpl discountService;
    @RequestMapping(value = "/discount.class",method = RequestMethod.GET)
    public ModelAndView showClass(String id){
        ModelMap map=new ModelMap();
        List<Clazz> list = discountService.getClazzByDiscount(id);
        map.put("result",list);
        return new ModelAndView("/discount",map);
    }
    @RequestMapping(value = "/discount.shop",method = RequestMethod.GET)
    public ModelAndView showShop(String id){
        ModelMap map=new ModelMap();
        List<Shop> list = discountService.getShopByDiscount(id);
        map.put("result",list);
        return new ModelAndView("/discount",map);
    }
    @RequestMapping(value = "/discount.product",method = RequestMethod.GET)
    public ModelAndView showProduct(String id){
        ModelMap map=new ModelMap();
        List<Product> list = discountService.getProductByDiscount(id);
        map.put("result",list);
        return new ModelAndView("/discount",map);
    }
}
