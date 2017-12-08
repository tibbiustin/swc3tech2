package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by coag on 02-11-2017.
 */
@Controller
public class StudentController {

    @Autowired
    StudentRepository sr;

    @Autowired
    CourseRepository cr;

    @GetMapping("/add")
    @ResponseBody
    public Student addStudent(
            @RequestParam(name = "name", defaultValue = "NO_NAME")
                    String name,
            @RequestParam(name = "email", defaultValue = "NO_EMAIL")
                    String email,
            @RequestParam(name = "grade", defaultValue = "NULL")
                    Integer grade,
            @RequestParam(name = "course", defaultValue = "NO_COURSE")
                    String courseName) {


        Course c = cr.findByCourseName(courseName);
        if (c == null) {
            c = new Course();
            c.setCourseName(courseName);
            cr.save(c);
        }


        Student s = new Student();
        s.setName(name);
        s.setEmail(email);
        s.setGrade(grade);
        s.setCourse(c);
        sr.save(s);


        return s;
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Student> showAllStudents() {
        final List<Student> myList = new ArrayList<>();
        Iterable<Student> isr = sr.findAll();
        isr.forEach((S) -> {
            myList.add(S);
        });
        System.out.println(myList);
        return myList;
    }

}
