package com.nhnacademy.student.Controller;


import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentViewController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        if(Objects.isNull(id)){
            throw new RuntimeException("parameter [id] : null ");
        }

        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException(id);
        }
        req.setAttribute("student",student);

        return "/student/view.jsp";
    }
}
