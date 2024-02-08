package com.nhnacademy.student.exception;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 22/02/2023
 */
public class DuplicateStudentIdException extends RuntimeException {
    public DuplicateStudentIdException(String id) {
        super("학생 아이디 중복: "+id);
    }
}
