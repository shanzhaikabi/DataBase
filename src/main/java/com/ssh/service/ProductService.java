package com.ssh.service;


import com.ssh.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> showProductByName(String name);

    List<Product> showProductByClassId(String classId);

    List<Product> showProductByShopId(String shopId);

    List<Product> showProductDetailHavingCustomer(String id,String CustomerId);

    Product get(String id);

    String editProduct(Product product);

    String deleteProduct(String id);

    String addProduct(Product product);
}
