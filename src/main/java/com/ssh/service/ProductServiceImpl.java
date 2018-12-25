package com.ssh.service;

import com.ssh.entity.Product;
import com.ssh.respository.ProductRepositoryImpl;
import com.ssh.utils.SearchUtils;
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
        List<Product> productList = productRepository.findByName(name);
        return getModelAndView(productList);
    }

    public ModelAndView ShowProductByClassId(String classId) {
        List<Product> productList = productRepository.findByClass(classId);
        return getModelAndView(productList);
    }

    public ModelAndView ShowProductByShopId(String shopId) {
        List<Product> productList = productRepository.findByShop(shopId);
        return getModelAndView(productList);
    }

    private ModelAndView getModelAndView(List<Product> productList) {
        ModelMap map = new ModelMap();
        if (productList.size() > 0) {
            map.put("result", SearchUtils.search_result_product(productList));
        }
        else{
            map.put("result", "抱歉，未能找到相关结果");
        }
        return new ModelAndView("/result",map);
    }
}
