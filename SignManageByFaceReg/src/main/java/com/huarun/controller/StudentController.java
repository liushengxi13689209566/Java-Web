package com.huarun.controller;

import com.huarun.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private CourseStudentService courseStudentService;

    @RequestMapping(value = "/delOneStudentInCourse", method = RequestMethod.GET)
    public String delOneStudentInCourse(int course_id, String student_id) {

        System.out.println("进入delOneStudentInCourse");
        System.out.println("course_id == " + course_id);
        System.out.println("student_id == " + student_id);

        courseStudentService.delOneStudentInCourse(course_id, student_id);

        return "StudentInfoShow";
    }
}
