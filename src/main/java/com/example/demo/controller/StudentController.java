package com.example.demo.controller;


import com.example.demo.data.StudentNodePath;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.fabric.StudentsFabric;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentRepository studentRepository;
    private final StudentsFabric fabric = new StudentsFabric();

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/match/haveLessons")
    public String matchStudentsWithLessons() {
        return studentRepository.matchStudentsWithLessons().toString();
    }

    @GetMapping("/match")
    public String match() {
        return studentRepository.matchAllStudents().toString();
    }

    @GetMapping("/put")
    public String put(){
        studentRepository.saveAll(fabric.generateRandomListOfStudents(7));
        return "Put has done";
    }

    @GetMapping("/pathLength")
    public String pathLength() {
        final List<StudentNodePath> studentNodePathList = new ArrayList<>();
        final List<Student> students = studentRepository.matchStudentsWithLessons();
        students.forEach(s1 -> students.forEach(s2 -> {
            if(!s1.equals(s2)){
                Integer pathLength = studentRepository.shortestPath(s1, s2);
                if (pathLength != null) {
                    StudentNodePath path = new StudentNodePath(s1, s2, pathLength);
                    if(!studentNodePathList.contains(path)){
                        studentNodePathList.add(path);
                    }
                }
            }
        }));
        studentNodePathList.sort(Comparator.comparingInt(StudentNodePath::pathLength));
        return studentNodePathList.toString();
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public String deleteAll(){
        studentRepository.deleteAllStudents();
        return "All students are deleted";
    }
}
