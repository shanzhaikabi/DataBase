package com.ssh.respository;

import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class DiscountRepositoryImpl implements DiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    public Discount load(Integer id) {
        return (Discount) getCurrentSession().load(Discount.class,id);
    }

    public Discount get(Integer id) {
        return (Discount) getCurrentSession().get(Discount.class,id);
    }

    public List<Discount> findAll() {
        return null;
    }

    public void persist(Discount entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Discount entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Discount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Discount entity) {
        getCurrentSession().delete(entity);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public List getAllForShop(String shopId) {
        List list = (List) getCurrentSession().createQuery(
                "from Discount dis,Shopdiscount sd where dis.discountRule = 'shop' and dis.discountType = sd.discountType and sd.shopId = ?").setParameter(0,shopId)
                .list().stream().map(user -> ((Object[])user)[0]).collect(Collectors.toList());
        flush();
        return list == null ? new ArrayList<>() : list;
    }

    public List getAllForClass(String classId) {
        List list = (List) getCurrentSession().createQuery(
                "from Discount dis,Classdiscount sd where dis.discountRule = 'class' and dis.discountType = sd.discountType and sd.classId = ?").setParameter(0,classId)
                .list().stream().map(user -> ((Object[])user)[0]).collect(Collectors.toList());
        return list == null ? new ArrayList<>() : list;
    }

    public List getAllForProduct(String productId) {
        List list = (List) getCurrentSession().createQuery(
                "from Discount dis,Productdiscount sd where dis.discountRule = 'product' and dis.discountType = sd.discountType and sd.productId = ?").setParameter(0,productId)
                .list().stream().map(user -> ((Object[])user)[0]).collect(Collectors.toList());
        return list == null ? new ArrayList<>() : list;
    }

    public List<Discount> getAllForAll() {
        Criteria c = getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("discountRule","all"));
        return c.list();
    }
}
