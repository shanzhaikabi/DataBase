package com.ssh.respository;

import com.ssh.entity.Ordermaster;
import com.ssh.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrdermasterRepositoryImpl implements OrdermasterRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session openSession() {
        return this.sessionFactory.openSession();
    }

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Ordermaster load(Integer id) {
        return (Ordermaster)getCurrentSession().load(Ordermaster.class,id);
    }

    public Ordermaster get(Integer id) {
        return (Ordermaster)getCurrentSession().get(Ordermaster.class,id);
    }

    public List<Ordermaster> findAll() {
        return null;
    }

    public void persist(Ordermaster entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Ordermaster entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Ordermaster entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Ordermaster entity) {
        getCurrentSession().delete(entity);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public List<Ordermaster> showMyOrder(String customerId) {
        return getCurrentSession().createCriteria(Ordermaster.class).add(Restrictions.eq("customerId",customerId)).list();
    }
}
