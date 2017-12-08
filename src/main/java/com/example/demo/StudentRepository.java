package com.example.demo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by coag on 02-11-2017.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}
