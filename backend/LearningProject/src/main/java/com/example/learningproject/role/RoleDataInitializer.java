package com.example.learningproject.role;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDataInitializer {
    private Logger logger = LoggerFactory.getLogger(RoleDataInitializer.class);

    @Autowired
    private RoleRepository roleRepository;

    private static final List<String> DEFAULT_ROLES =
            List.of("STUDENT", "TEACHER", "ADMIN");

    @PostConstruct  // Its running after start of server ( i guess)
    public void initDefaultRoles() {
        for (String roleName : DEFAULT_ROLES) {
            if (!roleRepository.existsByRoleName(roleName)) {
                Role role = new Role(roleName);
                roleRepository.save(role);
                logger.info("Role " + roleName + " created");
            }
        }
    }
}