package com.ssh.respository;

import com.ssh.entity.Customer;
import com.ssh.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Customer load(String id) {
        return (Customer)getCurrentSession().load(Customer.class,id);
    }

    public Customer get(String id) {
        return (Customer)getCurrentSession().get(Customer.class,id);
    }

    public List<Customer> findAll() {
        return null;
    }

    public void persist(Customer entity) {
        getCurrentSession().persist(entity);
    }

    public String save(Customer entity) {
        return (String)getCurrentSession().save(entity);
    }

    public void saveOrUpdate(Customer entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        getCurrentSession().delete(id);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
