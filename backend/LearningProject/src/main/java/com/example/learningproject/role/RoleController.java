package com.example.learningproject.role;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole/{id}/{role}")
    public ResponseEntity<String> addRole(@PathVariable Long id, @PathVariable String role) {
        Map<String,String> response = roleService.addRoleToUser(id, role);
        logger.info(response.toString());
        if (response.get("succes") != null) {
            return new ResponseEntity<>(response.get("succes"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response.get("error"), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/deleteRole/{id}/{role}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id, @PathVariable String role){
        Map<String,String> response = roleService.removeRoleFromUser(id, role);
        logger.info(response.toString());
        if (response.get("succes") != null) {
            return new ResponseEntity<>(response.get("succes"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(response.get("error"), HttpStatus.BAD_REQUEST);
        }
    }

}
