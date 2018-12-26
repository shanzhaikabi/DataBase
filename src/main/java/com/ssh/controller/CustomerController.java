package com.ssh.controller;

import com.ssh.entity.Discount;
import com.ssh.entity.Discountdetail;
import com.ssh.service.DiscountServiceImpl;
import com.ssh.utils.DiscountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    DiscountServiceImpl discountService;

    @RequestMapping(value = "/customer",method = RequestMethod.GET)
    public ModelAndView showMyDiscounts(String id){
        ModelMap map=new ModelMap();
        List<Object[]> availableList = discountService.getAvailableDiscountAndDetailFromUser(id);
        List<Object[]> usedList = discountService.getUsedDiscountAndDetailFromUser(id);
        map.put("result", DiscountUtils.mydiscount_haveList(availableList)+DiscountUtils.mydiscount_used(usedList));
        return new ModelAndView("/discount",map);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView becomeCustomer(HttpServletResponse response,String id){
        response.addCookie(new Cookie("customerId",id));
        return showMyDiscounts(id);
    }
}
