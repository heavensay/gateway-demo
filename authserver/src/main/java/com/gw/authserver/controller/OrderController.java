package com.gw.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lijianyu
 * @date 2018/10/17 14:43
 */

@Controller
@RequestMapping("order")
public class OrderController {

    @GetMapping("/queryOrder")
    @ResponseBody
    public String getOrder(){
        return "order1";
    }

}
