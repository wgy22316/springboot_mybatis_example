package com.my.springboot.springboot1.mapper;

import com.my.springboot.springboot1.dto.UserDTO;
import com.my.springboot.springboot1.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    /**
     * 通过id 查询用户
     * @param id
     * @return
     */
    User selectUserById(@Param("id") Integer id);

    /**
     * 通过userName 查询用户
     * @param userName
     * @return
     */
    UserDTO selectUserByName(String userName);
}