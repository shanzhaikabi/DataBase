package com.ssh.service;

import com.ssh.entity.Clazz;
import com.ssh.respository.ClazzRepositoryImpl;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    ClazzRepositoryImpl classRepository;

    public List<Clazz> ShowClassByName(String name) {
        //Transaction tx = classRepository.getCurrentSession().beginTransaction();
        List classList = classRepository.findByName(name);
        //tx.commit();
        return classList;
    }

    public String GetNameById(String id) {
        //Transaction tx = classRepository.getCurrentSession().beginTransaction();
        Clazz clazz = classRepository.get(id);
        //tx.commit();
        return clazz == null ? null : clazz.getClassName();
    }

    public Clazz GetClassById(String id) {
        //Transaction tx = classRepository.getCurrentSession().beginTransaction();
        Clazz clazz = classRepository.get(id);
        //tx.commit();
        return clazz;
    }
}
