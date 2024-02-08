package com.nhnacademy.student.exception;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 22/02/2023
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String id) {
        super("학생 못 찾음 : "+id);
    }
}
