package com.ssh.service;

import com.ssh.entity.Clazz;
import com.ssh.respository.ClazzRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    ClazzRepositoryImpl classRepository;

    public List<Clazz> ShowClassByName(String name) {
        List classList = classRepository.findByName(name);
        return classList;
    }

    public String GetNameById(String id) {
        Clazz clazz = classRepository.get(id);
        return clazz == null ? null : clazz.getClassName();
    }

    public Clazz GetClassById(String id) {
        Clazz clazz = classRepository.get(id);
        return clazz;
    }

}
