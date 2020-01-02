package com.qf.service;

import com.qf.entity.User;

import java.util.List;

public interface UserService {

    public User getAllUser(String email,String password);

    public int addUser(User user);

    public User getUserByEmail(String email);

    public int updateUserByEmail(User user);

    public User getUserByPassword(String password);
}
