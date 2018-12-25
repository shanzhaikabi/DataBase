package com.ssh.service;

import org.springframework.web.servlet.ModelAndView;

public interface ShopService {
    /**
     *
     * @param name
     * @return
     */
    public ModelAndView ShowShopByName(String name);
}
