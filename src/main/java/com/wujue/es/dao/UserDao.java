package com.wujue.es.dao;

import com.wujue.es.entity.Hobby;
import com.wujue.es.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author wujue
 * @version 1.0
 * @date 2020/11/25 下午2:42
 * @since jdk11
 */
public interface UserDao extends ElasticsearchRepository<User,String> {

//    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}},\"id\": {\"query\": \"?1\"}}}")
    List<User> findUserByNameAndId(String name, String id);
    List<User> findUserByName(String name);


    List<User> findUsersByHobbyListEquals(List<Hobby> hobbyList);



}
