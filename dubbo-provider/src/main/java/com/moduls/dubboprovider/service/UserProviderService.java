package com.moduls.dubboprovider.service;

import com.moduls.dubboprovider.pojo.User;

/**
 *  这是服务提供者自己调用的内部方法接口
 */
public interface UserProviderService {

    public User getUserById(Integer id);

    public User getOrdersByUserCode(Integer id);

    public Integer insertOneUser(User user);
}
