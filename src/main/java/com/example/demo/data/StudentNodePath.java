package com.example.demo.data;

import com.example.demo.model.Student;

import java.util.Objects;

public record StudentNodePath (Student student1, Student student2, Integer pathLength){
    @Override
    public String toString() {
        return "StudentNodePath{" +
                student1.getName() +
                ',' + student2.getName() +
                ", length =" + pathLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof StudentNodePath path)){
            return false;
        }
        boolean pair = Objects.equals(student1, path.student1) && Objects.equals(student2, path.student2) ||
                Objects.equals(student2, path.student1) && Objects.equals(student1, path.student2);
        return pair && Objects.equals(pathLength, path.pathLength);
    }

    @Override
    public int hashCode() {
        int result = student1 != null ? student1.hashCode() : 0;
        result = 31 * result + (student2 != null ? student2.hashCode() : 0);
        result = 31 * result + (pathLength != null ? pathLength.hashCode() : 0);
        return result;
    }
}
