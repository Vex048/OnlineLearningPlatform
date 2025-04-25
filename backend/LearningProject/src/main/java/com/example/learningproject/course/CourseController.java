package com.example.learningproject.course;


import com.example.learningproject.user.UserController;
import com.example.learningproject.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {

        ApiResponse<Course> response = courseService.addCourse(course);
        if(response.isSuccessful()) {
            logger.info(response.getSuccess().get(0));
            return new ResponseEntity<>(response.getSuccess().get(0), HttpStatus.OK);

        }
        else {
                for(String s: response.getErrors()){
                    logger.error(s);
                    return new ResponseEntity<>(s, HttpStatus.BAD_REQUEST);
                }
            }

//        if (response.get("success") != null) {
//            logger.info(response.get("success"));
//            return new ResponseEntity<>(response.get("success"), HttpStatus.OK);
//        }
//        else {
//            logger.info(response.get("error"));
//            return new ResponseEntity<>(response.get("error"), HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>("No succeses or errors", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
