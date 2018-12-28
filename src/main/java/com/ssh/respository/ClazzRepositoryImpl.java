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
public class ClazzRepositoryImpl implements ClazzRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Clazz load(String id) {
        return (Clazz)getCurrentSession().load(Clazz.class,id);
    }

    public Clazz get(String id) {
        return (Clazz) getCurrentSession().get(Clazz.class,id);
    }

    public List<Clazz> findAll() {
        return getCurrentSession().createCriteria(Clazz.class).list();
    }

    public List<Clazz> findByName(String name){
        Criteria c = getCurrentSession().createCriteria(Clazz.class).add(Restrictions.like("className","%"+name+"%"));
        return c.list() == null ? new ArrayList<>() : c.list();
    }

    public void persist(Clazz entity) {
        getCurrentSession().persist(entity);
    }

    public String save(Clazz entity) {
        return (String) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Clazz entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Clazz entity) {
        getCurrentSession().delete(entity);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
