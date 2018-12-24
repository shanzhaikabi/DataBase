package com.ssh.respository;

import com.ssh.entity.Clazz;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClazzRepositoryImpl implements ClazzRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public Clazz load(String id) {
        return (Clazz)getCurrentSession().load(Clazz.class,id);
    }

    public Clazz get(String id) {
        return (Clazz) getCurrentSession().get(Clazz.class,id);
    }

    public List<Clazz> findAll() {
        return null;
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

    public void delete(String id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
