package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Objects;
import java.util.Set;

public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Relationship(type = "has_lesson_of", direction = Relationship.Direction.INCOMING)
    private Set<Student> studentSet;

    public Lesson() {
    }

    public Lesson(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, studentSet);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Lesson lesson)) {
            return false;
        }
        return Objects.equals(this.id, lesson.id) && Objects.equals(title, lesson.title)
                && Objects.equals(studentSet, lesson.studentSet);
    }

    @Override
    public String toString() {
        return title + ' ' + id;
    }
}
