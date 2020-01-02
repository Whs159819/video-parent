package com.qf.controller;

import com.qf.entity.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, Model model) throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        request.getSession().setAttribute("userName",username);
       // model.addAttribute("userName",username);
        Admin admin = adminService.selAdminBy(username, password);
        System.out.println(admin.toString());
        if(admin!=null){
            return "success";
        }else{
            return "false";
        }
    }

    @RequestMapping("/exit")

    public String exit(){
        return "behind/login";
    }

}
