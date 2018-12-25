package com.ssh.respository;

import com.ssh.entity.Classdiscount;
import com.ssh.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ClassdiscountRepositoryImpl implements ClassdiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }


    public Classdiscount load(Integer id) {
        return (Classdiscount)getCurrentSession().load(Classdiscount.class,id);
    }

    public Classdiscount get(Integer id) {
        return (Classdiscount)getCurrentSession().get(Classdiscount.class,id);
    }

    public List<Product> findAll() {
        return null;
    }

    public void persist(Classdiscount entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Classdiscount entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Classdiscount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Integer id) {
        getCurrentSession().delete(id);
    }
    public void flush() {
        getCurrentSession().flush();
    }
}

