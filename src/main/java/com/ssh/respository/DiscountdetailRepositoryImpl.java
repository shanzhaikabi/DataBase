package com.ssh.respository;

import com.ssh.entity.Discount;
import com.ssh.entity.Discountdetail;
import com.ssh.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountdetailRepositoryImpl implements DiscountdetailRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public Discountdetail load(Integer id) {
        return (Discountdetail) getCurrentSession().load(Discountdetail.class,id);
    }

    public Discountdetail get(Integer id) {
        return (Discountdetail) getCurrentSession().get(Discountdetail.class,id);
    }

    public List<Discountdetail> findAll() {
        return null;
    }

    public void persist(Discountdetail entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Discountdetail entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Discountdetail entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Integer id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public List<Discountdetail> getDiscountDetailByCustomerId(String customerId) {
        Criteria c = getCurrentSession().createCriteria(Discountdetail.class)
                .add(Restrictions.eq("customerId",customerId));
        flush();
        return c.list();
    }
}
