package com.gw.serverone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lijianyu
 * @date 2018/10/16 11:46
 */

@Controller
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("/echo")
    @ResponseBody
    public String echo(){
        return "hello world server1";
    }

}
