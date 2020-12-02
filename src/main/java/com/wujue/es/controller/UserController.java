package com.wujue.es.controller;

import com.wujue.es.entity.User;
import com.wujue.es.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wujue
 * @version 1.0
 * @date 2020/11/25 下午4:16
 * @since jdk11
 */
@RestController
public class UserController {

    @Autowired
    UserService testService;

    @RequestMapping("findAll")
    public Iterable<User> findAll() {

        return testService.findAll();
    }

    @RequestMapping("list")
    public String save() {
        List<User> list = null;
        testService.saveEs(list);

        return "success";
    }

    @RequestMapping("save")
    public void save(User bean) {
        testService.saveEs(bean);
    }

    @RequestMapping("findByName")
    public List<User> findByName(String name,String id) {
        return testService.findUserByNameAndId(name,id);
    }



    @RequestMapping("findAllMysql")
    public Iterable<User> findAllMysql() {

        return testService.findAllMysql();
    }

    @RequestMapping("listMysql")
    public String saveMysql() {
        List<User> list = null;
        testService.saveMysql(list);

        return "success";
    }

    @RequestMapping("saveMysql")
    public void saveMysql(User bean) {
        testService.saveMysql(bean);
    }

    @RequestMapping("findByNameMysql")
    public List<User> findByNameMysql(String name,String id) {
        return testService.findUserByNameAndIdMysql(name,id);
    }
}
