package com.example.learningproject.unit;


import com.example.learningproject.role.Role;
import com.example.learningproject.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Test
    public void CreatingUserWithEmptyConstructor() {
        User user = new User();
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
    }

    @Test
    public void CreatingUserWithoutRolesConstructor() {
        String email = "emial";
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        User user = new User(
                email, password, username, firstName, lastName
        );
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getRoles()).isEqualTo(new HashSet<Role>());
    }

    @Test
    public void CreatingUserWithRolesConstructor() {
        String email = "emial";
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("STUDENT"));
        roles.add(new Role("USER"));

        User user = new User(email, password, username, firstName, lastName, roles);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.CountRoles()).isEqualTo(2);
        assertThat(user.getRoles()).isEqualTo(roles);
    }

    @Test
    public void ToStringUserTestWithoutRoles() {
        String email = "emial";
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        User user = new User(email, password, username, firstName, lastName);
        assertThat(user.toString()).isEqualTo(
                "User{" +
                        "id=" + null +
                        ", email='" + email + '\'' +
                        ", username='" + username + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", roles=" + "[]" + '\'' +
                        '}'
        );

    }

    @Test
    public void ToStringUserTestWithRoles() {
        String email = "emial";
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("STUDENT"));
        String roleString = "Role{id=null, roleName=STUDENT, users=null}";
        User user = new User(email, password, username,
                firstName, lastName, roles);
        assertThat(user.toString()).isEqualTo(
                "User{" +
                        "id=" + null +
                        ", email='" + email + '\'' +
                        ", username='" + username + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", roles=" + "[" + roleString + "]" + '\'' +
                        '}'
        );
    }
}
