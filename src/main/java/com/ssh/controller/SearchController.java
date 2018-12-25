package com.ssh.controller;

import com.ssh.service.ProductServiceImpl;
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

    @RequestMapping(value = "/search.do",method = RequestMethod.POST)
    public ModelAndView search(String keyword,String searchCheck) throws Exception {
        ModelMap map=new ModelMap();
        if (searchCheck.equals("product")){
            return productService.ShowProductBySearchingName(keyword);
        }
        else if (searchCheck.equals("class")){
            //TODO:class
        }
        else if (searchCheck.equals("shop")){
            //TODO:shop
        }
        else{
            throw new Exception();
        }
        return new ModelAndView("mainPage",map);
    }
}
