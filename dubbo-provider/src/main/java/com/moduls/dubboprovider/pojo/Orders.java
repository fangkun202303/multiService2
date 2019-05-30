package com.moduls.dubboprovider.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Orders implements Serializable{

    public Integer id;

    public String code;

    public String name;

    public Integer userId;

    public Orders (String code,String name,Integer userId){
        this.code = code;
        this.name=name;
        this.userId=userId;
    }
}
