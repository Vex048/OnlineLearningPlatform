package com.example.learningproject.role;

import com.example.learningproject.utils.ApiResponse;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/addRole/{id}/{role}")
    public ResponseEntity<String> addRole(@PathVariable Long id, @PathVariable String role) {
        ApiResponse<Role> response = roleService.addRoleToUser(id,role);
        //Map<String,String> response = roleService.addRoleToUser(id, role);
        logger.info(response.toString());
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
    @PostMapping("/deleteRole/{id}/{role}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id, @PathVariable String role){
        ApiResponse<Role> response = roleService.removeRoleFromUser(id, role);
        logger.info(response.toString());
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
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id){
        ApiResponse<Role> response = roleService.getRole(id);
        logger.info(response.toString());
        if(response.isSuccessful()) {
            logger.info(response.getSuccess().get(0));
            return new ResponseEntity<>(response.getData(),HttpStatus.OK);
        }
        else {
            if (!response.getErrors().isEmpty()) {
                String error = response.getErrors().get(0);
                logger.error(error);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/updateRole")
    public ResponseEntity<String> updateRole(@RequestBody Role role){
        ApiResponse<Role> response = roleService.updateRole(role);
        logger.info(response.toString());
        if(response.isSuccessful()) {
            logger.info(response.getSuccess().get(0));
            return new ResponseEntity<>("Role updated succesfully",HttpStatus.OK);
        }
        else {
            if (!response.getErrors().isEmpty()) {
                String error = response.getErrors().get(0);
                logger.error(error);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
