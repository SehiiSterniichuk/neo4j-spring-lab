package com.example.demo.data;

import com.example.demo.model.Lesson;

import java.util.Objects;

public record LessonNodePath (Lesson lesson1, Lesson lesson2, Integer pathLength){
    @Override
    public String toString() {
        return "LessonNodePath{" +
                lesson1.getTitle() +
                ',' + lesson2.getTitle() +
                ", length =" + pathLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof LessonNodePath path)){
            return false;
        }
        boolean pair = Objects.equals(lesson1, path.lesson1) && Objects.equals(lesson2, path.lesson2) ||
                Objects.equals(lesson2, path.lesson1) && Objects.equals(lesson1, path.lesson2);
        return  pair && Objects.equals(pathLength, path.pathLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesson1, lesson2, pathLength);
    }
}
