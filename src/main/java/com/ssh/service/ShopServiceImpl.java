package com.ssh.service;

import com.ssh.respository.ShopRepositoryImpl;
import com.ssh.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopRepositoryImpl shopRepository;

    public ModelAndView ShowShopByName(String name) {
        ModelMap map = new ModelMap();
        List shopList = shopRepository.findByName(name);
        if (shopList.size() > 0) {
            map.put("result", SearchUtils.search_result_shop(shopList));
        }
        else{
            map.put("result", "抱歉，未能找到相关结果");
        }
        return new ModelAndView("search",map);
    }
}
