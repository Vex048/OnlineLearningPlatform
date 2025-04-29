package com.example.learningproject.user;


import com.example.learningproject.course.Course;
import com.example.learningproject.enrollment.Enrollment;
import com.example.learningproject.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
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
        this.enrollments = new HashSet<>();
    }
    public User(String email, String password,String username, String firstName, String lastName,Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.enrollments = new HashSet<>();
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
    @JsonIgnoreProperties("users")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-enrollments")
    private Set<Enrollment> enrollments = new HashSet<>();



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

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }
    public void removeEnrollment(Enrollment enrollment) {
        this.enrollments.remove(enrollment);
    }
    public void addCourse(Course course){
        this.enrollments.add(new Enrollment(this,course,new Date()));
    }
    @JsonIgnore
    public Set<Course> getCourses() {
        Set<Course> courses = new HashSet<>();
        for(Enrollment enrollment : enrollments){
            courses.add(enrollment.getCourse());
        }
        return courses;
    }

//    public void addCourse(Course course) {
//        this.courses.add(course);
//    }

}
