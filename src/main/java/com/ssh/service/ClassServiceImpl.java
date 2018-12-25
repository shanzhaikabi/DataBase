package com.ssh.service;

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

    public ModelAndView ShowClassByName(String name) {
        ModelMap map = new ModelMap();
        List classList = classRepository.findByName(name);
        if (classList.size() > 0) {
            map.put("result", SearchUtils.search_result_class(classList));
        }
        else{
            map.put("result", "抱歉，未能找到相关结果");
        }
        return new ModelAndView("search",map);
    }
}
