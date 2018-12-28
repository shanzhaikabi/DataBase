package com.ssh.respository;

import com.ssh.entity.Product;
import com.ssh.entity.Productdiscount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ProductdiscountRepositoryImpl implements ProductdiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Productdiscount load(Integer id) {
        return (Productdiscount) getCurrentSession().load(Productdiscount.class,id);
    }

    public Productdiscount get(Integer id) {
        return (Productdiscount) getCurrentSession().get(Productdiscount.class,id);
    }

    public List<Productdiscount> findAll() {
        return null;
    }

    public void persist(Productdiscount entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Productdiscount entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Productdiscount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Productdiscount entity) {
        getCurrentSession().delete(entity);
    }


    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public List<Product> getProductByDiscount(int discountType) {
        List<Product> list =
                (List<Product>) getCurrentSession()
                        .createQuery("from Product c,Productdiscount cd where c.productId = cd.productId and cd.discountType = ?").setParameter(0,discountType)
                        .list().stream().map(user -> ((Object[])user)[0]).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Object[]> getDiscountAndDetailForShop(String shopId) {
        return getCurrentSession().createQuery("from Product c,Productdiscount cd,Discount d where c.productId = cd.productId and cd.discountType = d.discountType and d.shopId = ?").setParameter(0,shopId).list();
    }
}
