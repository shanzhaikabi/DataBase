package com.ssh.respository;

import com.ssh.entity.Product;

import java.util.List;

public interface ProductRepository extends DomainRepository<Product,String> {
    List<Product> findByName(String name);
    List<Product> findByClass(String classId);
    List<Product> findByShop(String shopId);
}
