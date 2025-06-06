package com.example.learningproject.enrollment;


import com.example.learningproject.course.Course;
import com.example.learningproject.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(EnrollmentId.class)
public class Enrollment {
    public Enrollment() {}
    public Enrollment(User user, Course course,Date date) {
        this.user = user;
        this.course = course;
        this.enrollment_date = date;
    }
    @Id
    @ManyToOne
    @JoinColumn(name="course_id")
    @JsonBackReference(value = "course-enrollments")
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference(value = "user-enrollments")
    private User user;

    @Column
    private Date enrollment_date;

    @Override
    public String toString() {
        return "Enrollment{ " + "enrollment_date: " +enrollment_date + " user: " + user + " course: " + course + "}";
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

    public Date getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(Date enrollment_date) {
        this.enrollment_date = enrollment_date;
    }
}
