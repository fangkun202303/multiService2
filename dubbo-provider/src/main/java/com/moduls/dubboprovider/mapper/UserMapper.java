package com.moduls.dubboprovider.mapper;

import com.moduls.dubboprovider.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select id, name from user where id=#{id} ")
    public User getUserById(Integer id);

    /**
     * 简单的关联查询
     * @param id
     * @return
     */
    @Select("select id, name from user where id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "ordersList", column = "id",many = @Many(select = "com.moduls.dubboprovider.mapper.OrderMapper.getOrdersByUserCode"))
    })
    public User getUserMsgById(Integer id);

    @Insert("insert into user(name) values(#{name})")
    public Integer insertOneUser(User user);
}
