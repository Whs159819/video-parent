package com.qf.service.impl;

import com.qf.dao.VideoMapper;
import com.qf.entity.Course;
import com.qf.entity.Speaker;
import com.qf.entity.Video;
import com.qf.entity.VideoQueryVo;
import com.qf.service.VideoService;
import com.qf.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoServiceimpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public Page<Video> getAllVideoByQuary(VideoQueryVo videoQueryVo) {
        // 设置查询条件,从哪一条数据开始查
        videoQueryVo.setStart((videoQueryVo.getPage() - 1) * videoQueryVo.getRows());

        // 查询数据结果集
        List<Video> list = videoMapper.getAllVideoByQuary(videoQueryVo);

        // 查询到的数据总条数
        Integer total = videoMapper.getVideoCount(videoQueryVo);

        // 封装返回的page对象
        Page<Video> videoPage = new Page<Video>();
        //查询数据
        videoPage.setRows(list);
        //当前页
        videoPage.setPage(videoQueryVo.getPage());
        //每页数据
        videoPage.setSize(videoQueryVo.getRows());
        //总记录数
        videoPage.setTotal(total);

        return videoPage;
    }

    public List<Speaker> getSpeaker() {
        return videoMapper.getSpeaker();
    }

    public List<Course> getCourse() {
        return videoMapper.getCourse();
    }

    public int getVideoCount(VideoQueryVo videoQueryVo) {
        return videoMapper.getVideoCount(videoQueryVo);
    }

    public Video getVideoById(Integer id) {
        return videoMapper.getVideoById(id);
    }

    public int updateVideoById(Video video) {
        return videoMapper.updateVideoById(video);
    }

    public int addVideo(Video video) {
        return videoMapper.addVideo(video);
    }

    public int delVideoById(Integer id) {
        return videoMapper.delVideoById(id);
    }

    public Video showVideo(Integer id){
        return videoMapper.showVideo(id);
    }
}
