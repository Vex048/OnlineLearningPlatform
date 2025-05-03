package com.example.learningproject.enrollment;


import com.example.learningproject.course.Course;
import com.example.learningproject.course.CourseController;
import com.example.learningproject.user.User;
import com.example.learningproject.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;



@RestController
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);
    @PostMapping("/addUserToCourse")
    public ResponseEntity<String> addUserToCourse(@RequestBody EnrollmentRequest enrollmentRequest) {
        User user = enrollmentRequest.getUser();
        Course course = enrollmentRequest.getCourse();
        Date enrollmentDate = enrollmentRequest.getEnrollmentDate();
        ApiResponse<Enrollment> response = enrollmentService.addUserToCourse(user, course,enrollmentDate);
        //Map<String,String> response = enrollmentService.addUserToCourse(user, course,date);


        if(response.isSuccessful()) {
            logger.info(response.getSuccess().get(0));
            return new ResponseEntity<>(response.getSuccess().get(0), HttpStatus.OK);

        }
        else {
            if (!response.getErrors().isEmpty()) {
                String error = response.getErrors().get(0);
                logger.error(error);
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("No succeses or errors", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("deleteUserFromCourse")
    public ResponseEntity<String> deleteUserFromCourse(@RequestBody EnrollmentRequest enrollmentRequest) {
        User user = enrollmentRequest.getUser();
        Course course = enrollmentRequest.getCourse();
        ApiResponse<Enrollment> response = enrollmentService.deleteUserFromCourse(user, course);
        if(response.isSuccessful()) {
            logger.info(response.getSuccess().get(0));
            return new ResponseEntity<>(response.getSuccess().get(0), HttpStatus.OK);
        }
        else {
            if (!response.getErrors().isEmpty()) {
                String error = response.getErrors().get(0);
                logger.error(error);
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("No succeses or errors", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

