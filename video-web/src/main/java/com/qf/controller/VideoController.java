package com.qf.controller;

import com.qf.entity.*;
import com.qf.service.CourseService;
import com.qf.service.VideoService;
import com.qf.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private CourseService courseService;

    @RequestMapping("/list")
    public String getAllByLimit(Model model,VideoQueryVo queryVo){
        List<Speaker> speakerList = videoService.getSpeaker();
        List<Course> courseList = videoService.getCourse();
        model.addAttribute("speakerList",speakerList);
        model.addAttribute("courseList",courseList);
        Page<Video> page = videoService.getAllVideoByQuary(queryVo);
        //System.out.println(page.getRows().toString());
        model.addAttribute("page",page);
        model.addAttribute("title",queryVo.getTitle());
        model.addAttribute("speaker_id",queryVo.getSpeaker_id());
        model.addAttribute("course_id",queryVo.getCourse_id());
        return "behind/videoList";

    }

    /**
     * 根据id查询用户,返回json格式数据
     */
    @RequestMapping("/edit")

    public String getVideoById(Model model,Integer id) {
        List<Course> courseList = videoService.getCourse();
        List<Speaker> speakerList = videoService.getSpeaker();
        model.addAttribute("courseList",courseList);
        model.addAttribute("speakerList",speakerList);

        Video video = videoService.getVideoById(id);
        model.addAttribute("video",video);
        return "behind/addVideo";
    }

    /**
     * 根据id查询用户,返回更新后客户的json格式数据
     */
    @RequestMapping("/update")
    public void updateVideoById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("utf-8");
        String ids = request.getParameter("id");
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        int speaker_id = Integer.parseInt(request.getParameter("speaker_id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        int time = Integer.parseInt(request.getParameter("time"));
        String image_url = request.getParameter("image_url");
        String video_url = request.getParameter("video_url");
        System.out.println(ids);

        if(ids!=null && !ids.equals("")){
            int id = Integer.parseInt(ids);
            int i = videoService.updateVideoById(new Video(id,title,detail,time,speaker_id,course_id,video_url,image_url));
            if(i==1){
                request.getRequestDispatcher("/video/list").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/error.jsp").forward(request,response);
            }
        }else{
            int i = videoService.addVideo(new Video(title,detail,time,speaker_id,course_id,video_url,image_url));
            if(i==1){
                request.getRequestDispatcher("/video/list").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/error.jsp").forward(request,response);
            }
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delVideoById(Integer id)  {
        int i = videoService.delVideoById(id);
        System.out.println(id+"-------");
        System.out.println(i+"?????");

        System.out.println("删除的方法");

        if(i==1){

            return "success";
        }else{
            return "false";
        }

    }

    @RequestMapping("/delBatchVideos")

    public void delBatchVideos(HttpServletRequest request,HttpServletResponse response, int [] ids) throws Exception {

        for (int id:ids) {
            videoService.delVideoById(id);
        }

        request.getRequestDispatcher("/video/list").forward(request,response);



    }

    @RequestMapping("/showVideo")
    public String showVideo(Model model,HttpServletRequest request) throws Exception {

        String ids = request.getParameter("video_id");
        String subject_name = request.getParameter("subject_name");

        int id = Integer.parseInt(ids);
        Video video = videoService.showVideo(id);
        System.out.println(id);
        System.out.println(subject_name);
        Course course = courseService.selCourseAndVideoBySubjectName(video.getCourse_id());
        System.out.println(video.toString());
        model.addAttribute("video",video);
        model.addAttribute("course",course);
        model.addAttribute("subject_name",subject_name);
       // model.addAttribute("course",subject.getCourseList());


        return "before/section";

    }

    @RequestMapping("/updatePlayNum")
    public void updatePlayNum(Integer id,Integer play_num) throws Exception {
        Video video = videoService.getVideoById(id);
        video.setPlay_num(play_num);
        int i = videoService.updateVideoById(video);


    }

}
