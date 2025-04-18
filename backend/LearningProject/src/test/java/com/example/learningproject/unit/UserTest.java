package com.example.learningproject.unit;


import com.example.learningproject.role.Role;
import com.example.learningproject.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    private User createUser() {
        String username = "user";
        String password = "password";
        String email = "email";
        String firstName = "firstName";
        String lastName = "lastName";
        return new User(email, password, username, firstName, lastName);
    }
    private User createUser(Set<Role> roles) {
        String username = "user";
        String password = "password";
        String email = "email";
        String firstName = "firstName";
        String lastName = "lastName";
        return new User(email, password, username, firstName, lastName,roles);
    }
    private List<String> getStrings() {
        return List.of("email", "password", "user", "firstName", "lastName");
    }
    @Test
    public void CreatingUserWithEmptyConstructor() {
        User user = new User();
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
    }

    @Test
    public void CreatingUserWithoutRolesConstructor() {

        User user = createUser();
        List<String> strings = getStrings();
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();

        assertThat(user.getEmail()).isEqualTo(strings.get(0));
        assertThat(user.getUsername()).isEqualTo(strings.get(2));
        assertThat(user.getPassword()).isEqualTo(strings.get(1));
        assertThat(user.getFirstName()).isEqualTo(strings.get(3));
        assertThat(user.getLastName()).isEqualTo(strings.get(4));
        assertThat(user.getRoles()).isEqualTo(new HashSet<Role>());
    }

    @Test
    public void CreatingUserWithRolesConstructor() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("STUDENT"));
        roles.add(new Role("USER"));
        User user  = createUser(roles);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNull();
        assertThat(user.CountRoles()).isEqualTo(2);
        assertThat(user.getRoles()).isEqualTo(roles);
    }

    @Test
    public void ToStringUserTestWithoutRoles() {
        User user = createUser();
        List<String> strings = getStrings();
        assertThat(user.toString()).isEqualTo(
                "User{" +
                        "id=" + null +
                        ", email='" + strings.get(0) + '\'' +
                        ", username='" + strings.get(2) + '\'' +
                        ", firstName='" + strings.get(3) + '\'' +
                        ", lastName='" + strings.get(4) + '\'' +
                        ", roles=" + "[]" + '\'' +
                        '}'
        );

    }

    @Test
    public void ToStringUserTestWithRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("STUDENT"));
        String roleString = "Role{id=null, roleName=STUDENT, users=null}";
        User user = createUser(roles);
        List<String> strings = getStrings();
        assertThat(user.toString()).isEqualTo(
                "User{" +
                        "id=" + null +
                        ", email='" + strings.get(0) + '\'' +
                        ", username='" +  strings.get(2) + '\'' +
                        ", firstName='" +  strings.get(3) + '\'' +
                        ", lastName='" +  strings.get(4) + '\'' +
                        ", roles=" + "[" + roleString + "]" + '\'' +
                        '}'
        );
    }
    @Test
    public void AddRoleTestWithoutRoles() {
        User user = createUser();
        Role newRole = new Role("STUDENT");
        user.addRole(newRole);
        assertThat(user).isNotNull();
        assertThat(user.CountRoles()).isEqualTo(1);
    }
    @Test
    public void AddRoleTestWithRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("STUDENT"));
        User user = createUser(roles);
        Role newRole = new Role("USER");
        user.addRole(newRole);
        assertThat(user).isNotNull();
        assertThat(user.CountRoles()).isEqualTo(2);
    }
    @Test
    public void RemoveRoleTest() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role("STUDENT");
        roles.add(role);
        User user = createUser(roles);
        assertThat(user).isNotNull();
        assertThat(user.CountRoles()).isEqualTo(1);
        user.removeRole(role);
        assertThat(user.CountRoles()).isEqualTo(0);
    }
    @Test
    public void TestSettingRolesFromSet(){
        User user = createUser();
        Set<Role> roles = new HashSet<>();
        assertThat(user.CountRoles()).isEqualTo(0);
        roles.add(new Role("STUDENT"));
        roles.add(new Role("USER"));
        roles.add(new Role("TEACHER"));
        user.setRoles(roles);
        assertThat(user).isNotNull();
        assertThat(user.CountRoles()).isEqualTo(3);

    }
}
