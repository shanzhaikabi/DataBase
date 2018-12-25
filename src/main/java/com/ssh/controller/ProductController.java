package com.ssh.controller;

import com.ssh.entity.Clazz;
import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.service.ProductServiceImpl;
import com.ssh.utils.ProductUtils;
import com.ssh.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public ModelAndView showProduct(String id){
        ModelMap map=new ModelMap();
        List targetList = productService.ShowProductDetail(id);
        if(targetList == null || targetList.size() < 4){
            return new ModelAndView("404 NOT FOUND");
        }
        else if (targetList.size() == 4){
            targetList.add(new ArrayList());
        }
        map.put("result", ProductUtils.detail_result_product((Product) targetList.get(0),(Clazz) targetList.get(1),(Shop)targetList.get(2),(List<Discount>)targetList.get(3),(List<Discount>)targetList.get(4)));
        return new ModelAndView("/product",map);
    }
}