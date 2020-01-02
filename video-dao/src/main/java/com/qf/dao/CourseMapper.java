package com.qf.dao;

import com.qf.entity.Course;
import com.qf.entity.CourseQueryVo;
import com.qf.entity.Subject;
import com.qf.entity.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {

    public List<Course> getAllCourse(CourseQueryVo courseQueryVo);

    public List<Subject> getAllSubject();

    public int getCourseCount();

    public Course getCourseById(Integer id);

    public int updateCourseById(Course course);

    public int addCourse(Course course);

    public int delCourseById(Integer id);

    public List<Course> selCourseBySubjectId(Integer subject_id);

    public List<Video> getAllVideo(Integer subject_id);

    public Subject selCourseAndVideoBySubjectId(Integer subject_id);

    public Course selCourseAndVideoBySubjectName(Integer course_id);

}
