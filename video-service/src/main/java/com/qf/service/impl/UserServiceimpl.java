package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getAllUser(String email,String password) {
        return userMapper.getAllUser(email,password);
    }

    public int addUser(User user){
        return userMapper.addUser(user);
    }

    public User getUserByEmail(String email){
        return userMapper.getUserByEmail(email);
    }

    public int updateUserByEmail(User user){
        return userMapper.updateUserByEmail(user);
    }

    public User getUserByPassword(String password){
        return userMapper.getUserByPassword(password);
    }
}
