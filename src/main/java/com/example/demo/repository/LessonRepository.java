package com.example.demo.repository;

import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends Neo4jRepository<Lesson, Long> {
    @Query("match(s:Student{name: :#{#student.name}}),(l:Lesson{title: :#{#lesson.title}})" +
            " create(s)-[h:has_lesson_of]->(l)")
    void connect(@Param("student") Student student, @Param("lesson") Lesson lesson);

    @Query("match(l1:Lesson)<-[:has_lesson_of]-(s:Student)-[:has_lesson_of]->(l2:Lesson) return l2")
    List<Lesson> match();

    @Query("match(l1:Lesson {title : :#{#lesson1.title}}), (l2:Team {title: :#{#lesson2.title}})," +
            " path = shortestPath((l1)-[:has_lesson_of*]-(l2)) return length(path)")
    Integer shortestPath(@Param("lesson1") Lesson lesson1, @Param("lesson2") Lesson lesson2);
}
