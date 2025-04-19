package com.example.learningproject.user;


import com.example.learningproject.course.Course;
import com.example.learningproject.role.Role;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user") // Apparently user word is resereved in h2
public class User {
    public User() {}

    public User(String email, String password,String username, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = new HashSet<>();
        this.courses = new HashSet<>();
    }
    public User(String email, String password,String username, String firstName, String lastName,Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.courses = new HashSet<>();
    }
    @Override
    public String toString(){
        return "User{" +
                "id=" + this.id +
                ", email='" + this.email + '\'' +
                ", username='" + this.username + '\'' +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", roles=" + this.roles + '\'' +
                ", roles=" + this.courses + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToMany
    @JoinTable(name="roles",joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    //CascadeType.ALl działa w ten sposób,
    // ze operacja na encji User jset przenoszona autoamtycznie na powiawane obiekty Course,
    // Czyli przy zapisie użytokowika, kurs także się zapisze
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }
    public int CountRoles() {
        return this.roles.size();
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role role) {
        this.roles.add(role);
    }
    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
    public Set<Course> getCourses() {
        return this.courses;
    }
}
