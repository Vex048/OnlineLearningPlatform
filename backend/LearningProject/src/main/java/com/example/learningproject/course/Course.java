package com.example.learningproject.course;


import com.example.learningproject.user.User;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
public class Course {

    public Course(String description, String title,User owner) {
        this.description = description;
        this.title = title;
        this.owner = owner;
    }
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
}
