package com.example.learningproject.role;


import com.example.learningproject.user.User;
import com.example.learningproject.user.UserRepository;
import com.example.learningproject.user.UserService;
import com.example.learningproject.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public ApiResponse<Role> addRoleToUser(Long id, String role_str) {
        ApiResponse<Role> response = new ApiResponse<>();
        Optional<User> user = userRepository.findById(id);
        Optional<Role> role = roleRepository.findByRoleName(role_str);
        if (user.isPresent() && role.isPresent()) {
            user.get().addRole(role.get());
            userRepository.save(user.get());
            response.addSuccess("Succesfully added role to " + user.get().getEmail());
            response.setSuccessful(true);
            //map.put("succes","Succesfully added role to " + user.get().getEmail());
            return response;
        }
        else {
            response.addError("There was an error adding role to user");
            response.setSuccessful(false);
            return response;
        }
    }
    public ApiResponse<Role> removeRoleFromUser(Long id, String role_str) {
        ApiResponse<Role> response = new ApiResponse<>();
        Optional<User> user = userRepository.findById(id);
        Optional<Role> role = roleRepository.findByRoleName(role_str);
        if (user.isPresent() && role.isPresent()){
            user.get().removeRole(role.get());
            userRepository.save(user.get());
            response.addSuccess("Succesfully deleted from " + user.get().getEmail());
            return response;
        }
        else {
            response.addError("There was an error deleting role from user");
            return response;
        }
    }
    public ApiResponse<Role> getRole(Long id) {
        ApiResponse<Role> response = new ApiResponse<>();
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            response.setData(role.get());
            response.setSuccessful(true);
            response.addSuccess("Succesfully retrieved role " + role.get().getRoleName());
        }
        else {
            response.setSuccessful(false);
            response.addError("There was an error retrieving role from user");
        }
        return response;
    }


}
