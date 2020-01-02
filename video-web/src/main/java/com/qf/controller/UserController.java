package com.qf.controller;

import com.qf.entity.User;
import com.qf.service.UserService;
import com.qf.util.ImageCut;
import com.qf.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loginUser")
    @ResponseBody
    public String toLoginBefore(HttpServletRequest request,User user)throws Exception{


       /*
        String email = request.getParameter("email");
        String password = request.getParameter("password");*/
        User user1 = userService.getAllUser(user.getEmail(), user.getPassword());
        if(user1!=null){
            request.getSession().setAttribute("userAccount",user1.getEmail());
            return "success";
        }else{
            return "error";
        }

    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(HttpServletRequest request,Model model){
        String email =(String) request.getSession().getAttribute("userAccount");
        model.addAttribute("email",email);
        return "/before/forget_password";
    }


    @RequestMapping("/insertUser")
    @ResponseBody
    public String insertUser(User user, HttpSession session){
        System.out.println("111111111112");
        int i = userService.addUser(user);
        session.setAttribute("userAccount",user.getEmail());
        System.out.println(i);
        if(i==1){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/showMyProfile")
    public String showMyProfile(HttpServletRequest request, Model model){

        System.out.println("22222222222");
        String eamil = (String)request.getSession().getAttribute("userAccount");
        System.out.println(eamil);
        User user = userService.getUserByEmail(eamil);
        //  System.out.println(user.toString());
        model.addAttribute("user",user);

        return "before/my_profile";

    }

    @RequestMapping("/validateEmail")
    @ResponseBody
    public String validateEmail(String email){

        System.out.println("22222222222");
        System.out.println(email);
        User user = userService.getUserByEmail(email);
        if(user !=null){
            return "error";
        }else{
            return "success";
        }
    }

    @RequestMapping("/changeProfile")
    public String updateUserByEmail(HttpServletRequest request, Model model){

        String eamil = (String)request.getSession().getAttribute("userAccount");
        User user = userService.getUserByEmail(eamil);
        model.addAttribute("user",user);
        return "before/change_profile";

    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request,User user, Model model){
        String eamil = (String)request.getSession().getAttribute("userAccount");
        user.setEmail(eamil);
        int i = userService.updateUserByEmail(user);
        user= userService.getUserByEmail(eamil);
        model.addAttribute("user",user);
        return "before/my_profile";

    }

    @RequestMapping("/changeAvatar")
    public String changeAvatar(HttpServletRequest request, Model model){

        String eamil = (String)request.getSession().getAttribute("userAccount");
        System.out.println(eamil);
        User user = userService.getUserByEmail(eamil);
        //  System.out.println(user.toString());
        model.addAttribute("user",user);

        return "before/change_avatar";

    }

    @RequestMapping("/passwordSafe")
    public String passwordSafe(HttpServletRequest request, Model model){

        String eamil = (String)request.getSession().getAttribute("userAccount");
        System.out.println(eamil);
        User user = userService.getUserByEmail(eamil);
        //  System.out.println(user.toString());
        model.addAttribute("user",user);

        return "before/password_safe";

    }

    @RequestMapping("/upLoadImage")
    public String upLoadImage(MultipartFile image_file, Model model,HttpSession session, double x1, double y1, double x2, double y2) throws Exception{

        int x =(int)(x1);
        int y =(int)(y1);
        int x3 =(int)(x2);
        int y3 =(int)(y2);
        File file = new File("E:\\tomcat\\apache-tomcat-9.0.24-file\\apache-tomcat-9.0.24\\webapps\\uploadfile\\images");
        String imgUrl = image_file.getOriginalFilename();
        imgUrl= UUID.randomUUID().toString().replace("-","")+imgUrl;
        image_file.transferTo(new File(file,imgUrl));
        ImageCut imageCut = new ImageCut();
        imageCut.cutImage("E:\\tomcat\\apache-tomcat-9.0.24-file\\apache-tomcat-9.0.24\\webapps\\uploadfile\\images"+"\\"+imgUrl,x,y,x3-x,y3-y);
        String email =(String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        user.setImgUrl(imgUrl);
        userService.updateUserByEmail(user);
        System.out.println(imgUrl);
        System.out.println(user.toString());
        model.addAttribute("user",user);
        return "before/my_profile";

    }

    @RequestMapping("/validatePassword")
    @ResponseBody
    public String upLoadImage(String password,HttpServletRequest request){

        String eamil = (String)request.getSession().getAttribute("userAccount");
        User user = userService.getAllUser(eamil,password);
        request.getSession().setAttribute("user",user);
        if(user !=null ){
            return "success";
        }else{
            return "false";
        }
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(HttpServletRequest request, Model model){
        String eamil = (String)request.getSession().getAttribute("userAccount");
        String password = request.getParameter("newPassword");
        User user = userService.getUserByEmail(eamil);
        user.setPassword(password);
        int i = userService.updateUserByEmail(user);
        model.addAttribute("user",user);
        return "before/my_profile";
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(String params, Model model,HttpServletRequest request){
        String email = request.getParameter("email");
        request.getSession().setAttribute("email",email);
        System.out.println(email+"--------");
        System.out.println(params);
        String code = MailUtils.getValidateCode(6);
        request.getSession().setAttribute("code",code);
        boolean b = MailUtils.sendMail("1041121185@qq.com",
                "你好，这是一封测试邮件，无需回复。",
                "测试邮件随机生成的验证码是：" + code);


        if(b){
            return "success";
        }else{
            return "hasNoUser";
        }
    }

    @RequestMapping("/validateEmailCode")
    public String validateEmailCode(HttpServletRequest request){

        String email = (String)request.getSession().getAttribute("email");
        request.getSession().setAttribute("email",email);
        String code = request.getParameter("code");
        String code1 = (String)request.getSession().getAttribute("code");
        System.out.println(code+"-----"+code1);
        if(code.equals(code1)){
            return "before/reset_password";
        }else{
            return "before/forget_password";
        }
    }

    @RequestMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request){
        String password = request.getParameter("password02");
        String email = (String)request.getSession().getAttribute("email");
        User user = userService.getUserByEmail(email);
        user.setPassword(password);
        int i = userService.updateUserByEmail(user);
        if(i==1){
            return "before/index";
        }else{
            return "404";
        }

    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
       request.getSession().removeAttribute("userAccount");
        return "before/index";

    }

    @RequestMapping("/loginOut2")
    public String loginOut2(HttpServletRequest request){
        request.getSession().removeAttribute("userAccount");
        request.getSession().removeAttribute("email");
        request.getSession().removeAttribute("user");
        return "before/index";

    }


}
