package com.ssh.respository;

import com.ssh.entity.Discountdetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DiscountdetailRepositoryImpl implements DiscountdetailRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
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

    public List<Discountdetail> getDiscountdetailByCustomerId(String customerId) {
        List list = getCurrentSession().createCriteria(Discountdetail.class)
                .add(Restrictions.eq("customerId",customerId)).list();
        return list;
    }

    public List<Discountdetail> getUsedDiscountdetailByCustomerId(String customerId){
        List list = getCurrentSession().createCriteria(Discountdetail.class)
                .add(Restrictions.eq("customerId",customerId))
                .add(Restrictions.ne("discountStatus","yes")).list();
        return list;
    }

    public List<Discountdetail> getAvailableDiscountdetailByCustomerId(String customerId){
        List list = getCurrentSession().createCriteria(Discountdetail.class)
                .add(Restrictions.eq("customerId",customerId))
                .add(Restrictions.eq("discountStatus","yes")).list();
        return list;
    }

    @Override
    public List<Object[]> getUsedDiscountAndDetailByCustomerId(String customerId) {
        List list = getCurrentSession().createQuery("from Discount d,Discountdetail dt where d.discountType = dt.discountType and dt.customerId = ? and dt.discountStatus != ?")
                .setParameter(0,customerId).setParameter(1,"yes").list();
        return list;
    }

    @Override
    public List<Object[]> getAvailableDiscountAndDetailByCustomerId(String customerId) {
        List list = getCurrentSession().createQuery("from Discount d,Discountdetail dt where d.discountType = dt.discountType and dt.customerId = ? and dt.discountStatus = ?")
                .setParameter(0,customerId).setParameter(1,"yes").list();
        return list;
    }

    public Discountdetail getDiscountByTypeAndCustomer(int discountType, String customerId) {
        Discountdetail discountdetail = (Discountdetail) getCurrentSession().createCriteria(Discountdetail.class)
                .add(Restrictions.eq("customerId",customerId))
                .add(Restrictions.eq("discountType",discountType))
                .add(Restrictions.eq("discountStatus","yes"))
                .uniqueResult();
        return discountdetail;
    }
}
