package com.gw.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lijianyu
 * @date 2018/10/19 17:43
 */
@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/echo")
    public String echo(){
        return "imlgdsdbr";
    }

    @GetMapping("/permisson")
    public String qeury(){
        return "Admin";
    }

}
