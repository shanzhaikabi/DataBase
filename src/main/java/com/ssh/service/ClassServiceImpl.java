package com.ssh.service;

import com.ssh.entity.Clazz;
import com.ssh.respository.ClazzRepositoryImpl;
import com.ssh.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    ClazzRepositoryImpl classRepository;

    public List<Clazz> ShowClassByName(String name) {
        List classList = classRepository.findByName(name);
        return classList;
    }


}
