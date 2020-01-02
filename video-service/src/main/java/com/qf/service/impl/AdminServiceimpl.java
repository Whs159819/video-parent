package com.qf.service.impl;

import com.qf.dao.AdminMapper;
import com.qf.entity.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceimpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public Admin selAdminBy(String username, String password) {

        return adminMapper.selAdminBy(username,password);
    }
}
