package com.example.learningproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    //private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public Long registerUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return user.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
