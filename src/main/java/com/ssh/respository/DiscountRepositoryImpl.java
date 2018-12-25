package com.ssh.respository;

import com.ssh.entity.Discount;
import com.ssh.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DiscountRepositoryImpl implements DiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    public Discount load(String id) {
        return (Discount) getCurrentSession().load(Discount.class,id);
    }

    public Discount get(String id) {
        return (Discount) getCurrentSession().get(Discount.class,id);
    }

    public List<Discount> findAll() {
        return null;
    }

    public void persist(Discount entity) {
        getCurrentSession().persist(entity);
    }

    public String save(Discount entity) {
        return (String) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Discount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public List<Discount> getAllForShop(String shopId) {
        List<Discount> list = getCurrentSession().createQuery(
                "from Discount dis,Shopdiscount sd where dis.discountRule = 'shop' and dis.discountType = sd.discountType and sd.shopId = ?").setParameter(0,shopId).list();
        return list == null ? new ArrayList<>() : list;
    }

    public List<Discount> getAllForClass(String classId) {
        /*Criteria c = getCurrentSession().createCriteria(Discount.class)
                .add(Restrictions.eq("discountRule","class"))
                .createAlias("Classdiscount","cd")
                .add(Restrictions.eq("cd.classId",classId));
        return c.list() == null ? new ArrayList<>() : c.list();*/
        List<Discount> list = getCurrentSession().createQuery(
                "from Discount dis,Classdiscount sd where dis.discountRule = 'class' and dis.discountType = sd.discountType and sd.classId = ?").setParameter(0,classId).list();
        return list == null ? new ArrayList<>() : list;
    }

    public List<Discount> getAllForProduct(String productId) {
        /*Criteria c = getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("discountRule","product"))
                .createAlias("productdiscount","pd")
                .add(Restrictions.eq("pd.classId",productId));
        return c.list() == null ? new ArrayList<>() : c.list();*/
        List<Discount> list = getCurrentSession().createQuery(
                "from Discount dis,Productdiscount sd where dis.discountRule = 'product' and dis.discountType = sd.discountType and sd.productId = ?").setParameter(0,productId).list();
        return list == null ? new ArrayList<>() : list;
    }

    public List<Discount> getAllForAll() {
        Criteria c = getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("discountRule","all"));
        return c.list();
    }
}
