package com.example.learningproject.user;

import com.example.learningproject.course.Course;
import com.example.learningproject.role.Role;
import com.example.learningproject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public HashMap<String, String> userExists(User user) {
        HashMap<String, String> erros = new HashMap<>();

        if (userRepository.existsByEmail(user.getEmail())) {
            erros.put("email","Email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            erros.put("username","Username already exists");
        }
        return erros;
    }
    public ApiResponse<User> checkForErros (List<User> users) {
        ApiResponse<User> response = new ApiResponse<>();
        for (User user : users) {
            logger.info(user.toString());
            HashMap<String,String> map = userExists(user);
            if (map.isEmpty()) {
                registerUser(user);
                response.addSuccess("User " + user.getEmail() + " successfully registered" );
                logger.info("User " + user.getEmail() + " successfully registered" );
            }
            else{
                for (String key : map.keySet()) {
                    response.addError("User " + user.getEmail()+": " + key + " already exists" );
                    logger.info("User " + user.getEmail() + ": "+ key + " already exists" );
                }
            }

        }
        if (response.getSuccess().size() == users.size()) {
            response.setSuccessful(true);
        } else if (!response.getErrors().isEmpty()) {
            response.setSuccessful(false);
            response.setMessage("Some errors occurred");
        }
        else {
            response.setSuccessful(false);
            response.setMessage("Erros occured in every user");
        }
        return response;
    }
    public ApiResponse<User> checkForErros (User user) {
        ApiResponse<User> response = new ApiResponse<>();
        logger.info(user.toString());
        HashMap<String,String> map = userExists(user);
        if (map.isEmpty()) {
            registerUser(user);
            response.addSuccess("User " + user.getEmail() + " successfully registered" );
            logger.info("User " + user.getEmail() + " successfully registered" );
        }
        else{
            for (String key : map.keySet()) {
                response.addError("User " + user.getEmail()+": " + key + " already exists" );
                logger.info("User " + user.getEmail() + ": "+ key + " already exists" );
            }
        }
        if (response.getErrors().size() == 0 && response.getSuccess().size() == 1) {
            response.setSuccessful(true);
        }
        else {
            response.setSuccessful(false);
            response.setMessage("Erros occured user");
        }
        return response;
    }



    public Long registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }


}
