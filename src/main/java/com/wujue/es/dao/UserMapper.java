package com.wujue.es.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujue.es.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wujue
 * @version 1.0
 * @date 2020/12/1 下午2:13
 * @since jdk11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
