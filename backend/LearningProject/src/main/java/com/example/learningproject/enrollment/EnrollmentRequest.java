package com.example.learningproject.enrollment;

import com.example.learningproject.course.Course;
import com.example.learningproject.user.User;

import java.util.Date;

public class EnrollmentRequest {
    private Course course;
    private User user;
    private Date enrollmentDate;
    public EnrollmentRequest(User user, Course course, Date enrollmentDate) {
        this.user = user;
        this.course = course;
        this.enrollmentDate = new Date();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
