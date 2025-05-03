package com.example.learningproject.course;


import com.example.learningproject.user.User;
import com.example.learningproject.user.UserController;
import com.example.learningproject.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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
            if (!response.getErrors().isEmpty()) {
                String error = response.getErrors().get(0);
                logger.error(error);
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
                }
            }

        return new ResponseEntity<>("No succeses or errors", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Course>> getCourse(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if(course.isPresent()) {
            return ResponseEntity.ok(course);
        }
        else {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteCourse")
    public ResponseEntity<String> deleteCourse(@RequestBody Course course) {
        ApiResponse<Course> response = courseService.deleteCourse(course);
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
    @PostMapping("/updateCourse")
    public ResponseEntity<String> updateCourse(@RequestBody Course course) {
        ApiResponse<Course> response = courseService.updateCourse(course);
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
