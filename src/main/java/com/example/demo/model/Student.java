package com.example.demo.model;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Objects;
import java.util.Set;

@Node
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    @Relationship(type = "has_lesson_of", direction = Relationship.Direction.OUTGOING)
    private Set<Lesson> lessons;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student student)) {
            return false;
        }
        return Objects.equals(this.id, student.id) && Objects.equals(name, student.name)
                && Objects.equals(age, student.age) && Objects.equals(lessons, student.lessons);
    }

    @Override
    public String toString() {
        return name + ' ' + id;
    }
}
