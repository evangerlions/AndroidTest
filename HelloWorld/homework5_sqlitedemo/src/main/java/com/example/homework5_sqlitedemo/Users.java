package com.example.homework5_sqlitedemo;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/25.
 */
//这个类用来记录用户数据以便存入数据库
public class Users {
    private  int id  = -1;
    private String name;
    private Date regDate;
    private int age;
    public Users(String name,int age){
        this.name = name;
        this.age = age;
        this.regDate = new Date();
    }
    public Users(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getRegDate() {
        return regDate;

    }

    public int getAge() {
        return age;
    }
}

