package com.ssh.service;

import com.ssh.entity.Product;
import com.ssh.respository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepositoryImpl productRepository;

    public ModelAndView ShowProductByName(String name){
        ModelMap map = new ModelMap();
        List<Product> productList = productRepository.findByName(name);
        if (productList.size() > 0) {
            map.put("result", productList);
            map.put("type", "product");
        }
        else{
            map.put("result", "抱歉，未能找到相关结果");
            map.put("type","error");
        }
        return new ModelAndView("/search",map);
    }

    public ModelAndView ShowProductByClassId(String classId) {
        return null;
    }

    public ModelAndView ShowProductByShopId(String shopId) {
        return null;
    }
}
