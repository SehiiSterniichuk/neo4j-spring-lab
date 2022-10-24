package com.example.demo.data;

import com.example.demo.model.Lesson;

public record LessonNodePath (Lesson lesson1, Lesson lesson2, Integer pathLength){
    @Override
    public String toString() {
        return "LessonNodePath{" +
                "lesson1=" + lesson1 +
                ", lesson2=" + lesson2 +
                ", pathLength=" + pathLength +
                '}';
    }
}
