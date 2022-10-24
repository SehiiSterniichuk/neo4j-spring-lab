package com.example.demo.controller;

import com.example.demo.data.LessonNodePath;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private LessonRepository lessonRepository;
    private StudentRepository studentRepository;
    private static final Random rand = new Random();

    private static final List<Lesson> lessons = List.of(
            new Lesson("Java"),
            new Lesson("C++"),
            new Lesson("Math"),
            new Lesson("Data Structure"),
            new Lesson("Discrete Math")
    );

    public LessonController(LessonRepository lessonRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/match")
    public String match(){
        return lessonRepository.matchLessonsWithStudents().toString();
    }

    @GetMapping("/matchAll")
    public String matchAll(){
        return lessonRepository.matchAll().toString();
    }

    @GetMapping("/put")
    public String put(){
        lessonRepository.saveAll(lessons);
        return "Put has done";
    }

    @RequestMapping(value = "/connectFreeStudents", method = RequestMethod.GET)
    public String connectFreeStudents(){
        var students = studentRepository.matchStudentsWithoutLessons();
        connect(students);
        return "Connect has done";
    }

    @RequestMapping(value = "/connectBusyStudents", method = RequestMethod.GET)
    public String connectBusyStudents(){
        var students = studentRepository.matchStudentsWithLessons();
        connect(students);
        return "Connect has done";
    }

    private void connect(List<Student> students){
        var lessons = lessonRepository.matchAll();
        int size = lessons.size();
        for (var student: students) {
            int randomLessonIndex = rand.nextInt(size);
            var lesson = lessons.get(randomLessonIndex);
            lessonRepository.connect(student, lesson);
        }
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public String deleteAll(){
        lessonRepository.deleteAllLessons();
        return "All lessons are deleted";
    }

    @GetMapping("/pathLength")
    public String pathLength() {
        final List<LessonNodePath> lessonNodePathList = new ArrayList<>();
        final List<Lesson> lessons = lessonRepository.matchLessonsWithStudents();
        lessons.forEach(l1 -> lessons.forEach(l2 -> {
            if(!l1.equals(l2)){
                Integer pathLength = lessonRepository.shortestPath(l1, l2);
                if (pathLength != null) {
                    LessonNodePath nodePath = new LessonNodePath(l1, l2, pathLength);
                    if(!lessonNodePathList.contains(nodePath)){
                        lessonNodePathList.add(nodePath);
                    }
                }
            }
        }));
        lessonNodePathList.sort(Comparator.comparingInt(LessonNodePath::pathLength));
        return lessonNodePathList.toString();
    }
}
