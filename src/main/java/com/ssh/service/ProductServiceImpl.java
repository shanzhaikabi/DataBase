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

    public ModelAndView ShowProductBySearchingName(String name){
        ModelMap map = new ModelMap();
        List<Product> productList = productRepository.findByName(name);
        map.put("result",productList);
        map.put("type","product");
        return new ModelAndView("/search",map);
    }
}
