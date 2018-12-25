package com.ssh.controller;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.service.ClassServiceImpl;
import com.ssh.service.ProductServiceImpl;
import com.ssh.service.ShopServiceImpl;
import com.ssh.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
            List<Product> productList = productService.ShowProductByName(keyword);
            if (productList.size() > 0) {
                map.put("result","抱歉，未能找到相关结果");
            }
            else{
                map.put("result", SearchUtils.search_result_product(productList));
            }
        }
        else if (searchCheck.equals("class")){
            List<Clazz> classList = classService.ShowClassByName(keyword);
            if (classList.size() > 0) {
                map.put("result","抱歉，未能找到相关结果");
            }
            else{
                map.put("result", SearchUtils.search_result_class(classList));
            }
        }
        else if (searchCheck.equals("shop")){
            List<Shop> shopList = shopService.ShowShopByName(keyword);
            if (shopList.size() > 0) {
                map.put("result","抱歉，未能找到相关结果");
            }
            else{
                map.put("result", SearchUtils.search_result_shop(shopList));
            }
        }
        else{
            throw new Exception();
        }
        return new ModelAndView("/search",map);
    }

    @RequestMapping(value = "/search.sp",method = RequestMethod.GET)
    public ModelAndView searchProductByShop(String id){
        ModelMap map=new ModelMap();
        List<Product> productList = productService.ShowProductByShopId(id);
        if (productList.size() > 0) {
            map.put("result","抱歉，未能找到相关结果");
        }
        else{
            map.put("result", SearchUtils.search_result_product(productList));
        }
        return new ModelAndView("/search",map);
    }

    @RequestMapping(value = "/search.cl",method = RequestMethod.GET)
    public ModelAndView searchProductByClass(String id){
        ModelMap map=new ModelMap();
        List<Product> productList = productService.ShowProductByClassId(id);
        if (productList.size() > 0) {
            map.put("result","抱歉，未能找到相关结果");
        }
        else{
            map.put("result", SearchUtils.search_result_product(productList));
        }
        return new ModelAndView("/search",map);
    }
}
