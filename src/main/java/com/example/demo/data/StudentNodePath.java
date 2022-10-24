package com.example.demo.data;

import com.example.demo.model.Student;

public record StudentNodePath (Student student1, Student student2, Integer pathLength){
    @Override
    public String toString() {
        return "StudentNodePath{" +
                "student1=" + student1 +
                ", student2=" + student2 +
                ", pathLength=" + pathLength +
                '}';
    }
}
