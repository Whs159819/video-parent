package com.qf.controller;

import com.qf.entity.Course;
import com.qf.entity.CourseQueryVo;
import com.qf.entity.Subject;
import com.qf.entity.Video;
import com.qf.service.CourseService;
import com.qf.util.Page;
import com.qf.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/courseList")
    public String getAllCourse(Model model, CourseQueryVo queryVo){
        List<Subject> subjectList = courseService.getAllSubject();
        model.addAttribute("subjectList",subjectList);
        Page<Course> page = courseService.getAllCourse(queryVo);
        model.addAttribute("page",page);
        return "behind/courseList";
    }

    @RequestMapping("/edit")
    public String getCourseById(Model model,Integer id) {
        List<Subject> subjectList = courseService.getAllSubject();
        model.addAttribute("subjectList",subjectList);
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        return "behind/addCourse";
}
    @RequestMapping("/update")
    public void updateAndAddCourse(HttpServletRequest request , HttpServletResponse response) throws  Exception{
        String ids = request.getParameter("id");
        String course_title = request.getParameter("course_title");
        String course_desc = request.getParameter("course_desc");
        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
        if(ids!=null && !ids.equals("")){
            int id = Integer.parseInt(ids);
            int i = courseService.updateCourseById(new Course(id, course_title, course_desc, subject_id));
            System.out.println(i+"**********"+id);
            if(i==1){
                request.getRequestDispatcher("/course/courseList").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/error.jspp").forward(request,response);
            }
        }else{
            int i = courseService.addCourse(new Course(course_title, course_desc, subject_id));
            if(i==1){
                request.getRequestDispatcher("/course/courseList").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/error.jspp").forward(request,response);
            }
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delCourseById(Integer id){
        int i = courseService.delCourseById(id);
        if(i==1){
            return "success";
        }else{
            return "false";
        }
    }

    @RequestMapping("/beforeToLogin")
    public String selCourseBySubjectId(Integer subject_id, HttpSession session){
        List<Subject> subjectList = courseService.getAllSubject();
       // model.addAttribute("subjectList",subjectList);
        session.setAttribute("subjectList",subjectList);

        return "before/index";
    }

    @RequestMapping("/course/{subject_id}")
    public String selCourseBySubjectId1(@PathVariable(name = "subject_id") Integer subject_id, Model model){
        List<Subject> subjectList = courseService.getAllSubject();
        model.addAttribute("subjectList",subjectList);
        Subject subject = courseService.selCourseAndVideoBySubjectId(subject_id);
        model.addAttribute("subject",subject);

        return "before/course";
    }

}
