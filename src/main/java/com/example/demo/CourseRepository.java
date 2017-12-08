package com.example.demo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by coag on 07-11-2017.
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findByCourseName(String courseName);
}
