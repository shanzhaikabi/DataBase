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
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public ModelAndView login(HttpServletResponse httpServletResponse, User user, String isUseCookie){
        if (null == isUseCookie || isUseCookie.length() == 0){
            httpServletResponse.addCookie(new Cookie("username",null));
            httpServletResponse.addCookie(new Cookie("password",null));
        }
        String password = user.getPassword();
        ModelAndView ret = userService.login(user);
        if (ret.getViewName().equals("mainPage")){//登陆成功
            if (null != isUseCookie && isUseCookie.length() > 0){
                httpServletResponse.addCookie(new Cookie("username",user.getUserid()));
                httpServletResponse.addCookie(new Cookie("password",password));
            }
            httpServletResponse.addCookie(new Cookie("userid",user.getUserid()));
        }
        else{
            httpServletResponse.addCookie(new Cookie("password",null));
            httpServletResponse.addCookie(new Cookie("userid",null));
        }
        return ret;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginWhenHaveCookie(@CookieValue(name = "username",defaultValue = "") String userName,@CookieValue(name = "password",defaultValue = "") String password) throws IOException {
        ModelMap map=new ModelMap();
        map.put("userName",userName);
        map.put("userPass",password);
        return new ModelAndView("login",map);
    }
}
