package com.ssh.respository;

import com.ssh.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CartRepositoryImpl implements CartRepository{
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Cart load(Integer id) {
        return (Cart) getCurrentSession().load(Cart.class,id);
    }

    @Override
    public Cart get(Integer id) {
        return (Cart) getCurrentSession().get(Cart.class,id);
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public void persist(Cart entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public Integer save(Cart entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(Cart entity) {
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
    public Cart getCartByCustomerIdAndProductId(String customerId, String productId) {
        Cart cart =
                (Cart) getCurrentSession().createCriteria(Cart.class)
                        .add(Restrictions.eq("customerId",customerId))
                        .add(Restrictions.eq("productId",productId))
                        .uniqueResult();
        return cart;
    }

    @Override
    public List<Object[]> getCartByCustomerId(String customerId) {
        List<Object[]> list = getCurrentSession().createQuery("from Product p,Cart c where p.productId = c.productId and c.customerId = ?").setParameter(0,customerId)
                .list();
        return list;
    }
}
