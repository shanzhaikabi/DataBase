package com.ssh.controller;

import com.ssh.service.ClassServiceImpl;
import com.ssh.service.ProductServiceImpl;
import com.ssh.service.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ClassServiceImpl classService;

    @Autowired
    private ShopServiceImpl shopService;

    @RequestMapping(value = "/search.do",method = RequestMethod.POST)
    public ModelAndView search(String keyword,String searchCheck) throws Exception {
        ModelMap map=new ModelMap();
        if (searchCheck.equals("product")){
            return productService.ShowProductByName(keyword);
        }
        else if (searchCheck.equals("class")){
            return classService.ShowClassByName(keyword);
        }
        else if (searchCheck.equals("shop")){
            return shopService.ShowShopByName(keyword);
        }
        else{
            throw new Exception();
        }
        //return new ModelAndView("mainPage",map);
    }
}
