package com.ssh.respository;

import com.ssh.entity.Shop;
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
public class ShopRepositoryImpl implements ShopRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Shop load(String id) {
        return (Shop) getCurrentSession().load(Shop.class,id);
    }

    public Shop get(String id) {
        return (Shop) getCurrentSession().get(Shop.class,id);
    }

    public List<Shop> findAll() {
        return null;
    }

    public List<Shop> findByName(String name){
        Criteria c = getCurrentSession().createCriteria(Shop.class).add(Restrictions.like("shopName","%"+name+"%"));
        return c.list() == null ? new ArrayList<>() : c.list();
    }

    public void persist(Shop entity) {
        getCurrentSession().persist(entity);
    }

    public String save(Shop entity) {
        return (String) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Shop entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
