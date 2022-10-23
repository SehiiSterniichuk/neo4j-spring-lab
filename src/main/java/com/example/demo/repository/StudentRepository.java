package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends Neo4jRepository<Student, Long> {
    @Query("match(s:Student)-[:has_lesson_of]->(l:Lesson)<-[:has_lesson_of]-(s2:Student) return s2")
    List<Student> match();

    @Query("match(s1:Student {name : :#{#student1.name}}), (s2:Student {name: :#{#Student2.name}})," +
            " path = shortestPath((s1)-[:has_lesson_of*]-(s2)) return length(path)")
    Integer shortestPath(@Param("student1") Student student1, @Param("student2") Student student2);
}
