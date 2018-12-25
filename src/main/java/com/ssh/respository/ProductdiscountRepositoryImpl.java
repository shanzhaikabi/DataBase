package com.ssh.respository;

import com.ssh.entity.Product;
import com.ssh.entity.Productdiscount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductdiscountRepositoryImpl implements ProductdiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public Productdiscount load(Integer id) {
        return (Productdiscount) getCurrentSession().load(Productdiscount.class,id);
    }

    public Productdiscount get(Integer id) {
        return (Productdiscount) getCurrentSession().get(Productdiscount.class,id);
    }

    public List<Product> findAll() {
        return null;
    }

    public void persist(Productdiscount entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Productdiscount entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Productdiscount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Integer id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
