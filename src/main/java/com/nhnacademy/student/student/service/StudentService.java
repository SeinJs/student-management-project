package com.nhnacademy.student.student.service;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.DuplicateStudentIdException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.student.repository.StudentRepository;
import com.nhnacademy.student.student.request.StudentModifyRequest;
import com.nhnacademy.student.student.request.StudentRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String id){
        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)){
            throw new StudentNotFoundException(id);
        }
        return student;
    }

    public void delete(String id){
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentList() {
        return studentRepository.getStudents();
    }

    public void register(Student student){
        if (studentRepository.existById(student.getId())){
            throw new DuplicateStudentIdException(student.getId());
        }
        studentRepository.save(student);
    }

    public void modify(Student student){
        studentRepository.update(student);
    }

    public Student toStudentByStudentRegisterRequest(StudentRegisterRequest studentRegisterRequest){
        return new Student(
                studentRegisterRequest.getId(),
                studentRegisterRequest.getName(),
                studentRegisterRequest.getGender(),
                studentRegisterRequest.getAge()
        );
    }

    public Student toStudentByStudentModifyRequest(String studentId, StudentModifyRequest studentModifyRequest){
        return new Student(
                studentId,
                studentModifyRequest.getName(),
                studentModifyRequest.getGender(),
                studentModifyRequest.getAge()
        );
    }
}
