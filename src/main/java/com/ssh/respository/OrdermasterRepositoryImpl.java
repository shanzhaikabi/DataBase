package com.ssh.respository;

import com.ssh.entity.Ordermaster;
import com.ssh.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdermasterRepositoryImpl implements OrdermasterRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }


    public Ordermaster load(String id) {
        return (Ordermaster)getCurrentSession().load(Ordermaster.class,id);
    }

    public Ordermaster get(String id) {
        return (Ordermaster)getCurrentSession().get(Ordermaster.class,id);
    }

    public List<Product> findAll() {
        return null;
    }

    public void persist(Ordermaster entity) {
        getCurrentSession().persist(entity);
    }

    public String save(Ordermaster entity) {
        return (String) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Ordermaster entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
