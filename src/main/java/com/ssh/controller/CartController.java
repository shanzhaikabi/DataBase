package com.ssh.controller;

import com.ssh.entity.Customer;
import com.ssh.service.CartServiceImpl;
import com.ssh.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartServiceImpl cartService;

    @Autowired
    CustomerServiceImpl customerService;

    @RequestMapping(value = "/addcart",method = RequestMethod.GET)
    public ModelAndView showMyDiscounts(@CookieValue(value = "customerId",defaultValue = "") String customerId, String productId, Integer quantity){
        ModelMap map=new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null){//请先登录
            return new ModelAndView("/login");
        }
        String result = cartService.addCart(customerId,productId,quantity);
        map.put("result", result);
        return new ModelAndView("/showcart",map);
    }

    @RequestMapping(value = "/showcart",method = RequestMethod.GET)
    public ModelAndView showMyDiscounts(@CookieValue(value = "customerId",defaultValue = "") String customerId){
        ModelMap map=new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null){//请先登录
            return new ModelAndView("/login");
        }
        List<Object[]> result = cartService.showCart(customerId);
        map.put("result", result);
        return new ModelAndView("/mycart",map);
    }
}
