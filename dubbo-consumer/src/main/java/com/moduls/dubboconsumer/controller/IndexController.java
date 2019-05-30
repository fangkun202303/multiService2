package com.moduls.dubboconsumer.controller;

//import com.alibaba.dubbo.config.annotation.Reference;
import com.moduls.dubboapi.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    /**
     *  将服务提供者的接口引入
     */
    @Reference(version = "1.0.0",check = false)
    private UserService userService;

    /**
     *  走一个伪代码
     * @param number
     * @return
     */
//    @GetMapping
    @RequestMapping(value = "/indexDemo/hello",method = {RequestMethod.GET})
    public String sayHello(Integer number){
        String str=userService.userStart(number);
        System.out.println(str);
        return str;
    }

}
