package com.example.learningproject.user;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/api/register")
    @ResponseBody
    public Long register(@RequestBody User user) {
        String user_string = user.toString();
        logger.info(user_string);
        return userService.registerUser(user);
    }

    @GetMapping("/api/getallusers")
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }
}
