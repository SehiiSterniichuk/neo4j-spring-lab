package com.example.demo.repository;

import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends Neo4jRepository<Lesson, Long> {
    @Query("MATCH(s:Student{name: :#{#student.name}}),(l:Lesson{title: :#{#lesson.title}}) " +
            "WHERE NOT (s)-[:has_lesson_of]->(l) " +
            "CREATE(s)-[:has_lesson_of]->(l)")
    void connect(@Param("student") Student student, @Param("lesson") Lesson lesson);

    @Query("MATCH (:Student)-[:has_lesson_of]->(l:Lesson) RETURN COLLECT(DISTINCT l)")
    List<Lesson> matchLessonsWithStudents();

    @Query("MATCH (l:Lesson) RETURN l;")
    List<Lesson> matchAll();

    @Query("MATCH (l:Lesson) DETACH DELETE l")
    void deleteAllLessons();

    @Query("match(l1:Lesson {title : :#{#lesson1.title}}), (l2:Lesson {title: :#{#lesson2.title}})," +
            " path = shortestPath((l1)-[:has_lesson_of*]-(l2)) return length(path)")
    Integer shortestPath(@Param("lesson1") Lesson lesson1, @Param("lesson2") Lesson lesson2);
}
