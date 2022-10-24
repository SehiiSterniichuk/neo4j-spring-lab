package com.example.demo.controller;

import com.example.demo.data.LessonNodePath;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.fabric.StudentsFabric;
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
    private final StudentsFabric fabric = new StudentsFabric();
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
    public List<Lesson> match(){
        return lessonRepository.matchLessonsWithStudents();
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public void connect(){
        var students = fabric.generateRandomListOfStudents(7);
        studentRepository.saveAll(students);
        lessonRepository.saveAll(lessons);
        int size = students.size();
        lessons.forEach(l->{// here we are connecting random amount of students to the lesson l
            for (int i = 0; i < rand.nextInt(size); i++) {
                int randomStudentIndex = rand.nextInt(size);
                Student student = students.get(randomStudentIndex);
                lessonRepository.connect(student,l);
            }
        });
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public void deleteAll(){
        lessonRepository.deleteAllLessons();
    }

    @GetMapping("/pathLength")
    public List<LessonNodePath> pathLength() {
        final List<LessonNodePath> lessonNodePathList = new ArrayList<>();
        final List<Lesson> lessons = match();
        lessons.forEach(l1 -> lessons.forEach(l2 -> {
            if(!l1.equals(l2)){
                Integer pathLength = lessonRepository.shortestPath(l1, l2);
                if (pathLength != null) {
                    lessonNodePathList.add(new LessonNodePath(l1, l2, pathLength));
                }
            }
        }));
        lessonNodePathList.sort(Comparator.comparingInt(LessonNodePath::pathLength));
        return lessonNodePathList;
    }
}
