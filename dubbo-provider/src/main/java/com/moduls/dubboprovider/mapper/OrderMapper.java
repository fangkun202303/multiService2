package com.moduls.dubboprovider.mapper;

import com.moduls.dubboprovider.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    @Select("select id, name, userid from orders where userid=#{userid}")
    public List<Orders> getOrdersByUserCode(Integer userid);

    @Insert("insert into orders (code, name, userid) values (#{code}, #{name}, #{userid} )")
    public Integer addOrders(Orders orders);
}
