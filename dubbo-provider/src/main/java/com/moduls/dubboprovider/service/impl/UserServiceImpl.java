package com.moduls.dubboprovider.service.impl;

//import com.alibaba.dubbo.config.annotation.Service;
import com.moduls.dubboapi.service.UserService;
import com.moduls.dubboprovider.exception.InsertException;
import com.moduls.dubboprovider.mapper.OrderMapper;
import com.moduls.dubboprovider.mapper.UserMapper;
import com.moduls.dubboprovider.pojo.Orders;
import com.moduls.dubboprovider.pojo.User;
import com.moduls.dubboprovider.service.UserProviderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(version = "1.0.0")
@Component  //当引入的是dubbo-spring-boot-starter：version-0.2.1版本时需要此注解将这个服务暴露,或者本服务需要在ioc中应用时
public class UserServiceImpl implements UserService, UserProviderService {

    public static List<User> list;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    static {
        list=new ArrayList<>();
        for(int x=0;x<10;x++){
            User u=new User();
            u.name="ID000"+x;
            u.id =x;
            list.add(u);
        }
    }

    /**
     * 这里是测试的伪代码
     * @param number
     * @return
     */
    @Override
    public String userStart(Integer number){
        Object[] objects = list.stream().filter(u -> u.id == number).toArray();
        User user= (User) objects[0];
        return "这是伪代码，消费者需要的用户的ID为："+user.name;
    }

    /**
     * 练习测试
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }


//    @Cacheable(value = "user",key = "'usergetOrdersByUserCode'+#p0")
    @Override
    public User getOrdersByUserCode(Integer id) {
        User user = userMapper.getUserMsgById(id);
//        redisTemplate.opsForValue().set("user",user);
        return user;
    }

    @Transactional
    @Override
    public Integer insertOneUser(User user) {
        Orders orders =new Orders("SH","毓雅",user.id);
//        try {
            Integer i = 1 / 0;
//        }catch (Exception e){
//            throw new RuntimeException(e.getMessage());
//        }
        orderMapper.addOrders(orders);
        return userMapper.insertOneUser(user);
    }

}
