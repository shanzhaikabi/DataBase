package com.ssh.service;


import org.springframework.web.servlet.ModelAndView;

public interface ProductService {
    /**
     *
     * @param name
     * @return
     */
    public ModelAndView ShowProductByName(String name);

    /**
     *
     * @param classId
     * @return
     */
    public ModelAndView ShowProductByClassId(String classId);

    /**
     *
     * @param shopId
     * @return
     */
    public ModelAndView ShowProductByShopId(String shopId);
}
