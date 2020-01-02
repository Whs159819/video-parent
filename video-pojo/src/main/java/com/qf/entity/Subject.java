package com.qf.entity;

import java.util.List;

public class Subject {

    private Integer id;
    private  String subject_name;

    private List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subject_name='" + subject_name + '\'' +
                ", courseList=" + courseList +
                '}';
    }

    public Subject(Integer id, String subject_name, List<Course> courseList) {
        this.id = id;
        this.subject_name = subject_name;
        this.courseList = courseList;
    }

    public Subject() {
    }
}
