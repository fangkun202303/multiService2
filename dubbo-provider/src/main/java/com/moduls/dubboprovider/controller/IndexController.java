package com.moduls.dubboprovider.controller;

import com.moduls.dubboprovider.pojo.User;
import com.moduls.dubboprovider.service.UserProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class IndexController {

    @Autowired
    private UserProviderService userService;


    @PostMapping("/gain/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/getOrdersByUserCode/{id}")
    public User getOrdersByUserCode(@PathVariable("id") Integer id){
        return  userService.getOrdersByUserCode(id);
    }

    @PostMapping("/insert")
    public String insertOneUser(User user){
        int i=1+1;
        Integer integer =userService.insertOneUser(user);
        return integer==1? "success":"失败了";
    }
}
