package com.moduls.dubboprovider.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private static final long userclassid=1L;

    public Integer id;

    public String name;

    public List<Orders> ordersList;


}
