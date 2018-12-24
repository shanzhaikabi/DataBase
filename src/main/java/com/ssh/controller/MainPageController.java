package com.ssh.controller;

import com.ssh.entity.User;
import com.ssh.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainPageController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/mainPage",method = RequestMethod.GET)
    public ModelAndView register(@CookieValue(name = "userid",defaultValue = "") String userid) {
        User user = userService.getUserFromCookie(userid);
        ModelMap map=new ModelMap();
        map.put("user",user);
        return new ModelAndView("mainPage",map);
    }
}
