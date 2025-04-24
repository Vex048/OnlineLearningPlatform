package com.example.learningproject.enrollment;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentId implements Serializable {
    private Long user;
    private Long course;

    public EnrollmentId(Long user_id, Long course_id) {
        this.user = user_id;
        this.course = course_id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(user, that.user) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, course);
    }
}
