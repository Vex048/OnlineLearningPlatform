package com.example.learningproject.course;

import com.example.learningproject.user.User;
import com.example.learningproject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;

    public Map<String,String> addCourse(Course course) {
        Map<String,String> response = new HashMap<>();
        String courseTitle = course.getTitle();
        String courseDescription = course.getDescription();
        User courseOwner = course.getOwner();
        if (courseTitle == null || courseDescription == null || courseOwner == null) {
            response.put("error","There are nulls in given course");
            return response;
        }
        else if (courseTitle.length() > 70) {
            response.put("error","Title exceeds 70 characters");
            return response;
        }
        else if (courseDescription.length() > 150) {
            response.put("error","Description exceeds 150 characters");
            return response;
        }
        else {
            courseOwner.addCourse(course);
            course.setOwner(courseOwner);
            courseRepository.save(course);
            response.put("success","Course added");
            return response;
        }
    }
}
