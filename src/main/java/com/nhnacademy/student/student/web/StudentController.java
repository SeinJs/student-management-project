package com.nhnacademy.student.student.web;

import com.nhnacademy.student.controller.BaseController;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.ValidationFailException;
import com.nhnacademy.student.student.request.StudentRegisterRequest;
import com.nhnacademy.student.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController implements BaseController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/delete.do")
    public String delete(HttpServletRequest request){
        String id = request.getParameter("id");
        log.debug("deleting student - id : {}", id);
        studentService.delete(id);
        return "redirect:/student/list.do";
    }

    @GetMapping("/list.do")
    public String list(Model model){
        List<Student> studentList = studentService.getStudentList();
        model.addAttribute("studentList", studentList);

        return "student/list";
    }

    @PostMapping("/register.do")
    public String register(@Valid StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationFailException(bindingResult);
        }
        Student student = studentService.toStudentByStudentRegisterRequest(studentRegisterRequest);
        studentService.register(student);
        log.debug("saved student: {}", student);
        return "redirect:/student/list.do";
    }

    @GetMapping("/register.do")
    public String registerForm(Model model){
        model.addAttribute(new Student());
        return "student/register";
    }

    @PostMapping("/update.do")
    public String update(@Valid StudentRegisterRequest studentRegisterRequest){
        Student student = studentService.toStudentByStudentRegisterRequest(studentRegisterRequest);
        studentService.modify(student);
        return "redirect:/student/view.do?id=" + studentRegisterRequest.getId();
    }

    @GetMapping("/update.do")
    public String updateForm(Model model, HttpServletRequest request){
        String id = request.getParameter("id");
        model.addAttribute("student", studentService.getStudent(id));
        return "student/register";
    }

    @GetMapping("/view.do")
    public String view(Model model, @RequestParam(name = "id") String id){
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student/view";
    }
}
