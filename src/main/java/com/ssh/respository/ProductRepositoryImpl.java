package com.ssh.respository;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    public List<Product> findByName(String name) {
        Criteria c = getCurrentSession().createCriteria(Product.class).add(Restrictions.like("productName","%" + name + "%"));
        List<Product> list = c.list();
        return list;
    }

    public List<Product> findByClass(String classId) {
        Criteria c = getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("classId",classId));
        List<Product> list = c.list();
        return list;
    }

    public List<Product> findByShop(String shopId) {
        Criteria c = getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("shopId",shopId));
        List<Product> list = c.list();
        return list;
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
