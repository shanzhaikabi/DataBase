package com.ssh.respository;

import com.ssh.entity.Product;
import com.ssh.entity.Shop;
import com.ssh.entity.Shopdiscount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ShopdiscountRepositoryImpl implements ShopdiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Shopdiscount load(Integer id) {
        return (Shopdiscount) getCurrentSession().load(Shopdiscount.class,id);
    }

    public Shopdiscount get(Integer id) {
        return (Shopdiscount) getCurrentSession().get(Shopdiscount.class,id);
    }

    public List<Shopdiscount> findAll() {
        return null;
    }

    public void persist(Shopdiscount entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Shopdiscount entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Shopdiscount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Shopdiscount entity) {
        getCurrentSession().delete(entity);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public List<Product> getShopProductByDiscount(int discountType){
        List<Product> list =
                (List<Product>) getCurrentSession()
                        .createQuery("from Product p,Shopdiscount cd where p.shopId = cd.shopId and cd.discountType = ?").setParameter(0,discountType)
                        .list().stream().map(user -> ((Object[])user)[0]).collect(Collectors.toList());
        return list;
    }

    public List<Shop> getShopByDiscount(int discountType){
        List<Shop> list =
                (List<Shop>) getCurrentSession()
                        .createQuery("from Shop p,Shopdiscount cd where p.shopId = cd.shopId and cd.discountType = ?").setParameter(0,discountType)
                        .list().stream().map(user -> ((Object[])user)[0]).collect(Collectors.toList());
        return list;
    }
}
