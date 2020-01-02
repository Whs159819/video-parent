package com.qf.controller;

import com.qf.entity.Speaker;
import com.qf.entity.SpeakerQueryVo;
import com.qf.service.SpeakerService;
import com.qf.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("/speakerList")
    public String speakerList(HttpServletRequest request, Model model, SpeakerQueryVo queryVo) {

        // 分页查询数据
        Page<Speaker> page = speakerService.findAllSpeakerBySpeakerQueryVo(queryVo);

        // 把分页查询的结果放到模型中
        model.addAttribute("page", page);

        return "behind/speakerList";
    }

    /**
     * 根据id查询用户,返回json格式数据
     */
    @RequestMapping("/edit")

    public String findSpeakerById(Model model,Integer id) {

        Speaker speaker = speakerService.findSpeakerById(id);
        model.addAttribute("speaker",speaker);
        return "behind/addSpeaker";
    }

    /**
     * 根据id查询用户,返回更新后客户的json格式数据
     */
    @RequestMapping("/update")
    public void updateSpeakerById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("utf-8");
        String ids = request.getParameter("id");
        String speaker_name = request.getParameter("speaker_name");
        String speaker_job = request.getParameter("speaker_job");
        String speaker_desc = request.getParameter("speaker_desc");
        System.out.println(ids);
        if(ids!=null && !ids.equals("")){
            int id = Integer.parseInt(ids);
            int i = speakerService.updateSpeakerById(new Speaker(id,speaker_name,speaker_desc,speaker_job));
            if(i==1){
                request.getRequestDispatcher("/speaker/speakerList").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/error.jsp").forward(request,response);
            }
        }else{
            int i = speakerService.addSpeaker(new Speaker(speaker_name,speaker_desc,speaker_job));
            if(i==1){
                request.getRequestDispatcher("/speaker/speakerList").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/error.jsp").forward(request,response);
            }
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delSpeakerById(Integer id)  {
        int i = speakerService.delSpeakerById(id);
        System.out.println(id+"-------");
        System.out.println(i+"?????");

        System.out.println("删除的方法");

        if(i==1){

            return "success";
        }else{
            return "false";
        }

    }

}
