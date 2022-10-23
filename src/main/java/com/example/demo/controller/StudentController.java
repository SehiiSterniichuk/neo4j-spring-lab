package com.example.demo.controller;


import com.example.demo.data.StudentNodePath;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/match")
    public List<Student> match() {
        return studentRepository.match();
    }

    @GetMapping("/pathLength")
    public List<StudentNodePath> pathLength() {
        final List<StudentNodePath> studentNodePathList = new ArrayList<>();
        final List<Student> students = match();
        students.forEach(s1 -> students.forEach(s2 -> {
            if(!s1.equals(s2)){
                Integer pathLength = studentRepository.shortestPath(s1, s2);
                if (pathLength != null) {
                    studentNodePathList.add(new StudentNodePath(s1, s2, pathLength));
                }
            }
        }));
        studentNodePathList.sort(Comparator.comparingInt(StudentNodePath::pathLength));
        return studentNodePathList;
    }
}
