package com.qf.service;

import com.qf.entity.Course;
import com.qf.entity.Speaker;
import com.qf.entity.Video;
import com.qf.entity.VideoQueryVo;
import com.qf.util.Page;

import java.util.List;

public interface VideoService {

    public Page<Video> getAllVideoByQuary(VideoQueryVo videoQueryVo);

    public List<Speaker> getSpeaker();

    public List<Course> getCourse();

    public int getVideoCount(VideoQueryVo videoQueryVo);

    public Video getVideoById(Integer id);

    public int updateVideoById(Video video);

    public int addVideo(Video video);

    public int delVideoById(Integer id);

    public Video showVideo(Integer id);
}
