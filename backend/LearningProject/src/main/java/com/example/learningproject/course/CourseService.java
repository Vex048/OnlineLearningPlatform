package com.example.learningproject.course;

import com.example.learningproject.user.User;
import com.example.learningproject.user.UserRepository;
import com.example.learningproject.user.UserService;
import com.example.learningproject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public ApiResponse<Course> addCourse(Course course) {
        ApiResponse<Course> apiResponse = new ApiResponse<>(course);
        //Map<String,String> response = new HashMap<>();
        String courseTitle = course.getTitle();
        String courseDescription = course.getDescription();
        User courseOwner = course.getOwner();
        if (courseTitle == null || courseDescription == null || courseOwner == null) {
            apiResponse.addError("There is no course title or description");
            //response.put("error","There are nulls in given course");
            return apiResponse;
        }
        else if (courseTitle.length() > 70) {
            apiResponse.addError("Course title is too long - excedes 70 characters");
            //response.put("error","Title exceeds 70 characters");
            return apiResponse;
        }
        else if (courseDescription.length() > 150) {
            apiResponse.addError("Course description is too long - exceeds 150 characters");
            //response.put("error","Description exceeds 150 characters");
            return apiResponse;
        }
        else {
            courseOwner.addCourse(course);
            course.setOwner(courseOwner);
            courseRepository.save(course);
            apiResponse.addSuccess("Course added");
            apiResponse.setSuccessful(true);
            //response.put("success","Course added");
            return apiResponse;
        }
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
}
