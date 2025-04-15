package com.example.learningproject.role;


import com.example.learningproject.user.User;
import com.example.learningproject.user.UserRepository;
import com.example.learningproject.user.UserService;
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


    public Map<String,String> addRoleToUser(Long id, String role_str) {
        Map<String, String> map = new HashMap<>();
        Optional<User> user = userRepository.findById(id);
        Optional<Role> role = roleRepository.findByRoleName(role_str);
        if (user.isPresent() && role.isPresent()) {
            user.get().addRole(role.get());
            userRepository.save(user.get());
            map.put("succes","Succesfully added role to " + user.get().getEmail());
            return map;
        }
        else {
            map.put("error","There was an error adding role to user");
            return map;
        }
    }
    public Map<String,String> removeRoleFromUser(Long id, String role_str) {
        Map<String, String> map = new HashMap<>();
        Optional<User> user = userRepository.findById(id);
        Optional<Role> role = roleRepository.findByRoleName(role_str);
        if (user.isPresent() && role.isPresent()){
            user.get().removeRole(role.get());
            userRepository.save(user.get());
            map.put("succes","Succesfully deleted from " + user.get().getEmail());
            return map;
        }
        else {
            map.put("error","There was an error deleting role from user");
            return map;
        }
    }


}
