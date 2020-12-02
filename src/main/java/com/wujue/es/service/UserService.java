package com.wujue.es.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujue.es.entity.User;

import java.util.List;

/**
 * @author wujue
 * @version 1.0
 * @date 2020/11/25 下午2:46
 * @since jdk11
 */
public interface UserService extends IService<User> {

    Iterable<User> findAll();

    void saveEs(List<User> list);

    void saveEs(User user);

    List<User> findUserByNameAndId(String name,String id);

    Iterable<User> findAllMysql();

    void saveMysql(List<User> list);

    void saveMysql(User user);

    List<User> findUserByNameAndIdMysql(String name,String id);
}
