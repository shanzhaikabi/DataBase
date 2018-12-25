package com.ssh.respository;

import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DiscountRepositoryImpl implements DiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    public Discount load(String id) {
        return (Discount) getCurrentSession().load(Discount.class,id);
    }

    public Discount get(String id) {
        return (Discount) getCurrentSession().get(Discount.class,id);
    }

    public List<Product> findAll() {
        return null;
    }

    public void persist(Discount entity) {
        getCurrentSession().persist(entity);
    }

    public String save(Discount entity) {
        return (String) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Discount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
