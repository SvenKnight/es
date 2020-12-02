package com.wujue.es.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author wujue
 * @version 1.0
 * @date 2020/11/25 下午2:22
 * @since jdk11
 */
@Document(indexName = "userinfo")
@TableName(value = "user",autoResultMap = true)
public class User implements Serializable {

    public User() {
    }

    public User(String id, String name, String userName, String phone, String email, List<Hobby> hobbyList) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.hobbyList = hobbyList;
    }

    @Id
    private String id;

    private String name;

    private String userName;

    private String phone;

    private String email;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Hobby> hobbyList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
    }
}
