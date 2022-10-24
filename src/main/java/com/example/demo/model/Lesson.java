package com.example.demo.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Objects;

public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

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

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Lesson lesson)) {
            return false;
        }
        return Objects.equals(title, lesson.title);
    }

    @Override
    public String toString() {
        return title + " id: " + id;
    }
}
