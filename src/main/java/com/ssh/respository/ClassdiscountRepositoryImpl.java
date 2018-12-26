package com.ssh.respository;

import com.ssh.entity.Classdiscount;
import com.ssh.entity.Clazz;
import com.ssh.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ClassdiscountRepositoryImpl implements ClassdiscountRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Classdiscount load(Integer id) {
        return (Classdiscount)getCurrentSession().load(Classdiscount.class,id);
    }

    public Classdiscount get(Integer id) {
        return (Classdiscount)getCurrentSession().get(Classdiscount.class,id);
    }

    public List<Classdiscount> findAll() {
        return null;
    }

    public void persist(Classdiscount entity) {
        getCurrentSession().persist(entity);
    }

    public Integer save(Classdiscount entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Classdiscount entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Integer id) {
        getCurrentSession().delete(id);
    }
    public void flush() {
        getCurrentSession().flush();
    }

    public List<Product> getClassProductByDiscount(int discountType) {
        List<Product> list =
                (List<Product>) getCurrentSession()
                        .createQuery("from Product c,Classdiscount cd where c.classId = cd.classId and cd.discountType = ?").setParameter(0,discountType)
                        .list().stream().map(user -> ((Object[])user)[0]).collect(Collectors.toList());
        return list;
    }
}

