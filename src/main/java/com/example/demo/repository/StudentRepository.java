package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends Neo4jRepository<Student, Long> {
    @Query("MATCH(s:Student)-[:has_lesson_of]->(l:Lesson) RETURN COLLECT(DISTINCT s)")
    List<Student> matchStudentsWithLessons();

    @Query("MATCH(s:Student),(l:Lesson) " +
            "WHERE NOT (s)-[:has_lesson_of]->(l) " +
            "RETURN COLLECT(DISTINCT s)")
    List<Student> matchStudentsWithoutLessons();

    @Query("MATCH(s1:Student {name : :#{#student1.name}}), (s2:Student {name: :#{#student2.name}})," +
            " path = shortestPath((s1)-[:has_lesson_of*]-(s2)) RETURN length(path)")
    Integer shortestPath(@Param("student1") Student student1, @Param("student2") Student student2);

    @Query("MATCH (s:Student) DETACH DELETE s")
    void deleteAllStudents();

    @Query("MATCH (s:Student) RETURN s;")
    List<Student> matchAllStudents();
}
