package com.qf.service.impl;

import com.qf.dao.CourseMapper;
import com.qf.entity.*;
import com.qf.service.CourseService;
import com.qf.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceimpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public Page<Course> getAllCourse(CourseQueryVo courseQueryVo) {

        courseQueryVo.setStart((courseQueryVo.getPage() - 1) * courseQueryVo.getRows());

        // 查询数据结果集
        List<Course> list = courseMapper.getAllCourse(courseQueryVo);

        // 查询到的数据总条数
        Integer total = courseMapper.getCourseCount();

        // 封装返回的page对象
        Page<Course> coursePage = new Page<Course>();
        //查询数据
        coursePage.setRows(list);
        //当前页
        coursePage.setPage(courseQueryVo.getPage());
        //每页数据
        coursePage.setSize(courseQueryVo.getRows());
        //总记录数
        coursePage.setTotal(total);

        return coursePage;
    }

    public List<Subject> getAllSubject() {
        return courseMapper.getAllSubject();
    }

    public List<Video> getAllVideo(Integer subject_id){
        return courseMapper.getAllVideo(subject_id);
    }

    public int getCourseCount() {
        return courseMapper.getCourseCount();
    }

    public Course getCourseById(Integer id) {
        return courseMapper.getCourseById(id);
    }

    public int updateCourseById(Course course) {
        return courseMapper.updateCourseById(course);
    }

    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    public int delCourseById(Integer id) {
        return courseMapper.delCourseById(id);
    }

    public List<Course> selCourseBySubjectId(Integer subject_id) {
        return courseMapper.selCourseBySubjectId(subject_id);
    }

    public Subject selCourseAndVideoBySubjectId(Integer subject_id){
        return courseMapper.selCourseAndVideoBySubjectId(subject_id);
    }

    public Course selCourseAndVideoBySubjectName(Integer course_id){
        return courseMapper.selCourseAndVideoBySubjectName(course_id);
    }


}
