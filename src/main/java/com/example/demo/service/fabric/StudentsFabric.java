package com.example.demo.service.fabric;

import com.example.demo.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentsFabric {
    private static final String[] names = {
            "Serhii",
            "Petro",
            "Bohdan",
            "Stanislav",
            "Vladislav",
            "Yulia",
            "Anastasia",
            "Viola",
            "Sophia",
            "Ira",
    };

    private static final Random rand = new Random();

    public List<Student> generateRandomListOfStudents(int number){
        List<Student> students = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            students.add(new Student(getRandomName(), getRandomAge()));
        }
        return students;
    }

    private String getRandomName(){
        int index = rand.nextInt(names.length);
        int randomNumber = rand.nextInt(10);
        return names[index] + randomNumber;
    }

    private int getRandomAge(){
        return 17 + rand.nextInt(6);
    }
}
