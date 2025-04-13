package com.example.learningproject.user;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/registerMultipleUsers")
    public ResponseEntity<HashMap<String,List<String>>> registerMultipleUsers(@RequestBody List<User> users) {
        HashMap<String,List<String>> response = userService.checkForErros(users);
        if (response.get("errors").isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.get("errors").size() == users.size()) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        else {
            return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
        }

    }

    @PostMapping("/register")
    public Long register(@RequestBody User user) {
        String user_string = user.toString();
        logger.info(user_string);
        return userService.registerUser(user);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
