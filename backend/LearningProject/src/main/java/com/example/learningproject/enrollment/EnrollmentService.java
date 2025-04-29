package com.example.learningproject.enrollment;


import com.example.learningproject.course.Course;
import com.example.learningproject.user.User;
import com.example.learningproject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public ApiResponse<Enrollment> addUserToCourse(User user, Course course, Date date) {
        ApiResponse<Enrollment> response = new ApiResponse<>();
        if (!user.getCourses().contains(course)) {
            enrollmentRepository.save(new Enrollment(user,course,date));
            response.setSuccessful(true);
            response.addSuccess("Enrollment added successfully");
        }
        else {
            response.setSuccessful(false);
            response.addSuccess("Enrollment already exists");
        }
        return response;
    }
}
