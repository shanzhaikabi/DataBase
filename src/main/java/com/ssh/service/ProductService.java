package com.ssh.service;


import com.ssh.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> ShowProductByName(String name);

    public List<Product> ShowProductByClassId(String classId);

    public List<Product> ShowProductByShopId(String shopId);
}
