package com.qf.dao;

import com.qf.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public User getAllUser(@Param("email") String email, @Param("password") String password);

    public int addUser(User user);

    public User getUserByEmail(String email);

    public int updateUserByEmail(User user);

    public User getUserByPassword(String password);



}
