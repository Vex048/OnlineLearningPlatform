package com.example.learningproject.user;
import com.example.learningproject.utils.ApiResponse;
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
    public ResponseEntity<String> registerMultipleUsers(@RequestBody List<User> users) {
        ApiResponse<User> response = userService.checkForErros(users);
        StringBuilder error_msg = new StringBuilder();
        if(response.isSuccessful()) {
            logger.info(response.getSuccess().get(0));
            return new ResponseEntity<>(response.getSuccess().get(0), HttpStatus.OK);

        }
        else {
            for (String error : response.getErrors()) {
                error_msg.append(error);
                error_msg.append("\n");
            }
            if (response.getMessage() != null) {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.MULTI_STATUS);
            }
            return new ResponseEntity<>(error_msg.toString(), HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String user_string = user.toString();
        logger.info(user_string);
        ApiResponse<User> response = userService.checkForErros(user);
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

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            //logger.info(user.toString());
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
    @PostMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        ApiResponse<User> response = userService.updateUser(user);
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
        return new ResponseEntity<>("Something went wrong in the server", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
