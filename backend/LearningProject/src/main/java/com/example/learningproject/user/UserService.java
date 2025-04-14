package com.example.learningproject.user;

import com.example.learningproject.role.Role;
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
    public HashMap<String,List<String>> checkForErros (List<User> users) {
        HashMap<String,List<String>> response = new HashMap<>();
        List<String> errors = new ArrayList<>();
        List<String> success = new ArrayList<>();
        for (User user : users) {
            logger.info(user.toString());
            HashMap<String,String> map = userExists(user);
            if (map.isEmpty()) {
                registerUser(user);
                success.add("User " + user.getEmail() + " successfully registered" );
                logger.info("User " + user.getEmail() + " successfully registered" );
            }
            else{
                for (String key : map.keySet()) {
                    errors.add("User " + user.getEmail()+": " + key + " already exists" );
                    logger.info("User " + user.getEmail() + ": "+ key + " already exists" );
                }
            }

        }
        response.put("errors",errors);
        response.put("succeses",success);
        return response;
    }
    public HashMap<String,List<String>> checkForErros (User user) {
        HashMap<String,List<String>> response = new HashMap<>();
        List<String> errors = new ArrayList<>();
        List<String> success = new ArrayList<>();
        logger.info(user.toString());
        HashMap<String,String> map = userExists(user);
        if (map.isEmpty()) {
            registerUser(user);
            success.add("User " + user.getEmail() + " successfully registered" );
            logger.info("User " + user.getEmail() + " successfully registered" );
        }
        else{
            for (String key : map.keySet()) {
                errors.add("User " + user.getEmail()+": " + key + " already exists" );
                logger.info("User " + user.getEmail() + ": "+ key + " already exists" );
            }
        }

        response.put("errors",errors);
        response.put("succeses",success);
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
