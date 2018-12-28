package com.ssh.controller;

import com.ssh.entity.Customer;
import com.ssh.entity.Ordermaster;
import com.ssh.service.CustomerServiceImpl;
import com.ssh.service.OrderServiceImpl;
import com.ssh.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(value = "/showorder.do",method = RequestMethod.POST)
    public ModelAndView showOneOrder(@CookieValue(value = "customerId",defaultValue = "") String customerId,int orderId) {
        ModelMap map = new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null) {//请先登录
            return new ModelAndView("login");
        }
        List<Object> uNeedThisList = orderService.getOrderdetail(orderId);
        Ordermaster ordermaster = (Ordermaster) uNeedThisList.get(0);
        if (ordermaster.getCustomerId() != customerId){//用户不一致
            return new ModelAndView("login");
        }
        List<Object[]> productList = (List<Object[]>) uNeedThisList.get(1);
        String result = OrderUtils.confirm_order_invoice(ordermaster,"detail") + OrderUtils.confirm_order_product(productList);
        map.put("result",result);
        return new ModelAndView("showorder",map);
    }

    @RequestMapping(value = "/showorder.do",method = RequestMethod.POST)
    public ModelAndView showOrder(@CookieValue(value = "customerId",defaultValue = "") String customerId){
        ModelMap map = new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null) {//请先登录
            return new ModelAndView("login");
        }
        List<Ordermaster> list = orderService.showMyOrder(customerId);
        String result = "";
        for (Ordermaster ordermaster : list) {
            result += OrderUtils.confirm_order_invoice(ordermaster,"all");
        }
        map.put("result",result);
        return new ModelAndView("showorder",map);
    }
}
