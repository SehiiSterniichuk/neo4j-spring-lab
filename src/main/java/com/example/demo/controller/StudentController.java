package com.example.demo.controller;


import com.example.demo.data.StudentNodePath;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
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

    @GetMapping("/pathLength")
    public String pathLength() {
        final List<StudentNodePath> studentNodePathList = new ArrayList<>();
        final List<Student> students = studentRepository.matchAllStudents();
        students.forEach(s1 -> students.forEach(s2 -> {
            if(!s1.equals(s2)){
                Integer pathLength = studentRepository.shortestPath(s1, s2);
                if (pathLength != null) {
                    studentNodePathList.add(new StudentNodePath(s1, s2, pathLength));
                }
            }
        }));
//        studentNodePathList.sort(Comparator.comparingInt(StudentNodePath::pathLength));
//        var s = new StudentNodePath(null, null, studentRepository.shortestPath(null, null));
//        studentNodePathList.add(s);
        return studentNodePathList.toString();
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public void deleteAll(){
        studentRepository.deleteAllStudents();
    }
}
