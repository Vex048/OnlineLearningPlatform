package com.example.learningproject.user;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

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
    public ResponseEntity<HashMap<String,List<String>>> register(@RequestBody User user) {
        String user_string = user.toString();
        logger.info(user_string);
        HashMap<String,List<String>> response = userService.checkForErros(user);
        if (response.get("errors").isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            logger.info(user.toString());
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(user.getId());
        if (existingUser.isPresent()) {
            logger.info(user.toString());
            userService.deleteUser(existingUser.get());
            return new ResponseEntity<>("Sucessful deletion", HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
