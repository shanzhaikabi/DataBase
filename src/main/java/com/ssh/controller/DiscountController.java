package com.ssh.controller;

import com.ssh.entity.*;
import com.ssh.respository.ProductRepositoryImpl;
import com.ssh.service.CustomerServiceImpl;
import com.ssh.service.DiscountServiceImpl;
import com.ssh.service.ProductServiceImpl;
import com.ssh.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DiscountController {
    @Autowired
    DiscountServiceImpl discountService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CustomerServiceImpl customerService;

    @RequestMapping(value = "/discount",method = RequestMethod.GET)
    public ModelAndView showProduct(@CookieValue(value = "customerId",defaultValue = "") String customerId, String id){
        ModelMap map=new ModelMap();
        Discount discount = discountService.get(id);
        Customer customer = customerService.get(customerId);
        String status = "";
        if (customer == null) status = "login";
        else{
            Discountdetail discountdetail = discountService.doCustomerHaveDiscount(id,customerId);
            if (discountdetail == null) status = "no";
            else status = "yes";
        }
        List<Product> list = discountService.getProductByDiscount(id);
        map.put("discount",discount);
        map.put("status",status);
        map.put("result", SearchUtils.search_result_product(list));
        return new ModelAndView("/discount",map);
    }
}
