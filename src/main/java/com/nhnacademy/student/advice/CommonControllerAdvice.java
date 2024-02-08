package com.nhnacademy.student.advice;

import com.nhnacademy.student.exception.DuplicateStudentIdException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.exception.ValidationFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String studentNotFound(StudentNotFoundException studentNotFoundException){
        log.error("error: {}", studentNotFoundException.getMessage());
        return "error/studentNotFound";
    }

    @ExceptionHandler(DuplicateStudentIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String duplicateStudentId(DuplicateStudentIdException duplicateStudentIdException){
        log.error("error: {}", duplicateStudentIdException.getMessage());
        return "error/duplicateStudentId";
    }

//    @ExceptionHandler(ValidationFailException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String validationFail(ValidationFailException validationFailException){
//        log.error("error: {}", validationFailException.getMessage());
//        return "error/validationFail";
//    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFound(){
        log.error("page not found");
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(Exception exception, Model model){
        model.addAttribute("exception", exception.getMessage());
        log.error("internal server error: {}",exception.getMessage());
        return "error/500";
    }
}
