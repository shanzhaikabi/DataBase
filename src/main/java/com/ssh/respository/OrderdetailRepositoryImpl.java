package com.ssh.respository;

import com.ssh.entity.Orderdetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderdetailRepositoryImpl implements OrderdetailRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Orderdetail load(Integer id) {
        return (Orderdetail) getCurrentSession().load(Orderdetail.class,id);
    }

    @Override
    public Orderdetail get(Integer id) {
        return (Orderdetail) getCurrentSession().get(Orderdetail.class,id);
    }

    @Override
    public List<Orderdetail> findAll() {
        return null;
    }

    @Override
    public void persist(Orderdetail entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public Integer save(Orderdetail entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(Orderdetail entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Integer id) {
        getCurrentSession().delete(id);
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }


    @Override
    public List<Object[]> getOrderdetailByOrderId(Integer orderId) {
        return (List<Object[]>) getCurrentSession().createQuery("from Product p,Orderdetail o where p.productId = o.productId and o.orderId = ?").setParameter(0,orderId);
    }
}
