package com.example.learningproject.enrollment;


import com.example.learningproject.course.Course;
import com.example.learningproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/addUserToCourse")
    public ResponseEntity<String> addUserToCourse(@RequestBody User user,@RequestBody Course course,@RequestBody Date date) {
        Map<String,String> response = enrollmentService.addUserToCourse(user, course,date);

        if (response.get("status").equals("success")) {
            return new ResponseEntity<>(response.get("message"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(response.get("message"), HttpStatus.CONFLICT);
        }
    }
}
