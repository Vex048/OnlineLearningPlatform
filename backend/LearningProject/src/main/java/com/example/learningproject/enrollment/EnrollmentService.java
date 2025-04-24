package com.example.learningproject.enrollment;


import com.example.learningproject.course.Course;
import com.example.learningproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Map<String,String> addUserToCourse(User user, Course course, Date date) {
        Map<String,String> map = new HashMap<>();
        return map;
    }
}
