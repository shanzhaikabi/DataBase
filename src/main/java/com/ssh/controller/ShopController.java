package com.ssh.controller;

import com.ssh.service.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShopController {
    @Autowired
    ShopServiceImpl shopService;

}
