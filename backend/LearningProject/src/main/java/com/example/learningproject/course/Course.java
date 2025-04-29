package com.example.learningproject.course;


import com.example.learningproject.enrollment.Enrollment;
import com.example.learningproject.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    public Course(String description, String title,User owner) {
        this.description = description;
        this.title = title;
        this.owner = owner;
    }
    public Course() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String description;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference(value = "course-enrollments")
    private Set<Enrollment> enrollments = new HashSet<>();

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Course{id=" + this.id + ", description: " + this.description + ", title:" + this.title + ", owner" + this.owner.toString() +  "}" ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getUsers() {
        Set<User> users = new HashSet<>();
        for (Enrollment enrollment : enrollments) {
            users.add(enrollment.getUser());
        }
        return users;
    }

}
