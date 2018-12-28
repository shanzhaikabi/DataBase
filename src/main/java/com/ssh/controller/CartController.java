package com.ssh.controller;

import com.ssh.entity.Customer;
import com.ssh.entity.Discount;
import com.ssh.service.CartServiceImpl;
import com.ssh.service.CustomerServiceImpl;
import com.ssh.service.DiscountServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

import static com.ssh.utils.CartUtils.showcart;

@Controller
public class CartController {
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    DiscountServiceImpl discountService;

    @RequestMapping(value = "/addcart.do",method = RequestMethod.GET)
    public ModelAndView addThatToMyCart(@CookieValue(value = "customerId",defaultValue = "") String customerId, String productId, Integer quantity){
        ModelMap map=new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null){//请先登录
            return new ModelAndView("login");
        }
        String result = cartService.addCart(customerId,productId,quantity);
        if(result.equals("success"))map.put("cartresult","添加购物车成功！");
        else map.put("cartresult","添加购物车失败！");
        return new ModelAndView("forward:showcart",map);
    }

    @RequestMapping(value = "/editcart.do",method = RequestMethod.GET)
    public ModelAndView addThatToMyCart(@CookieValue(value = "customerId",defaultValue = "") String customerId, Integer cartId, Integer quantity){
        ModelMap map=new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null){//请先登录
            return new ModelAndView("login");
        }
        cartService.editCart(cartId,quantity);
        return new ModelAndView("forward:showcart",map);
    }

    @RequestMapping(value = "/showcart",method = RequestMethod.GET)
    public ModelAndView showMyCart(@CookieValue(value = "customerId",defaultValue = "") String customerId){
        ModelMap map=new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null){//请先登录
            return new ModelAndView("login");
        }
        List<Object[]> result = cartService.showCart(customerId);
        map.put("result", showcart(result));
        return new ModelAndView("cart",map);
    }

    @RequestMapping(value = "/commitcart.do",method = RequestMethod.POST)
    public ModelAndView commitCart(@CookieValue(value = "customerId",defaultValue = "") String customerId, @RequestParam("cartId") String[] cartId){
        ModelMap map=new ModelMap();
        Customer customer = customerService.get(customerId);
        if (customer == null){//请先登录
            return new ModelAndView("login");
        }
        List<Integer> list = new ArrayList<>();
        for (String s : cartId) {
            s = s.replace(" ","");
            System.out.println(s);
            list.add(Integer.parseInt(s));
        }
        cartService.changeStatus(customerId,list);
        List<Object> iNeedThatList = cartService.calculateCart(customerId);
        String ans = OrderUtils.confirm_order_product((List<Object[]>) iNeedThatList.get(3));
        ans = ans + OrderUtils.confirm_order_discount((List<Discount>) iNeedThatList.get(2));
        ans = ans + OrderUtils.confirm_order_total((Integer) iNeedThatList.get(0),(Integer) iNeedThatList.get(1));
        map.put("result",ans);
        int tot = (int)iNeedThatList.get(0) - (int)iNeedThatList.get(1);
        Integer orderId = orderService.createOrder(customerId, (List<Object[]>) iNeedThatList.get(3),tot);
        discountService.useDiscount((List<Discount>) iNeedThatList.get(2),customerId,orderId);
        return new ModelAndView("showOrder",map);
    }

}
