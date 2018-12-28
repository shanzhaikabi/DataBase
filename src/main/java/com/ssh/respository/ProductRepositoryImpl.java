package com.ssh.respository;

import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
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
        return c.list() == null ? new ArrayList<>() : c.list();
    }

    public List<Product> findByClass(String classId) {
        Criteria c = getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("classId",classId));
        return c.list() == null ? new ArrayList<>() : c.list();
    }

    public List<Product> findByShop(String shopId) {
        Criteria c = getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("shopId", shopId));
        return c.list() == null ? new ArrayList<>() : c.list();
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

    @Override
    public void delete(Product entity) {
        getCurrentSession().delete(entity);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
