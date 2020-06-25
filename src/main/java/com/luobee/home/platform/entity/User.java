package com.luobee.home.platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PF_USER")
//前后端数据交互用的是json格式,对象转换为json数据,项目使用jpa来做实体类的持久化，jpa默认使用 hibernate,在jpa工作过程中，会创造代理类来继承此实体类,并添加handler和hibernateLazyInitializer这两个无须json化的属性,所以这里需要用JsonIgnoreProperties把这两个属性忽略掉
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User {
    @Id
    @Column(name = "GUID")
    //在此不使用主键自动生成策略,因为主键不是整型而是字符串,自动生成还需引用GenericGenerator,是hibernate中的注解
    private String GUID;
    private String realName;
    private String username;
    private String password;
    private String email;
    private int gender;
    private String salt;

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
