package com.ssh.respository;

import com.ssh.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public Product load(String id) {
        return (Product) getCurrentSession().load(Product.class,id);
    }

    public Product get(String id) {
        return (Product) getCurrentSession().get(Product.class,id);
    }

    public List<Product> findAll() {
        return null;
    }

    public void persist(Product entity) {
        getCurrentSession().persist(entity);
    }

    public String save(Product entity) {
        return (String) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Product entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
