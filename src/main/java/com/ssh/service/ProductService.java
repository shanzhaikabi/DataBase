package com.ssh.service;


import com.ssh.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> ShowProductByName(String name);
    List<Product> ShowProductByClassId(String classId);
    List<Product> ShowProductByShopId(String shopId);
    void addProduct(Product product);
}
