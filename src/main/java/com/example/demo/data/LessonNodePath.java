package com.example.demo.data;

import com.example.demo.model.Lesson;

public record LessonNodePath (Lesson lesson1, Lesson lesson2, Integer pathLength){
}
