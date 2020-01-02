package com.qf.dao;

import com.qf.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    public Admin selAdminBy(@Param("username") String username, @Param("password") String password);
}
