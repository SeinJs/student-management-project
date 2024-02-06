package com.nhnacademy.student.listener;



import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.JsonStudentRepository;
import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import org.apache.commons.math3.random.RandomDataGenerator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        //StudentRepository studentRepository = new MapStudentRepository();
        StudentRepository studentRepository = new JsonStudentRepository();
        for(int i=1; i<=10; i++){
            String id="student"+i;
            String name="아카데미"+i;
            int age = new RandomDataGenerator().nextInt(20,30);
            Gender gender = age%2==0 ? Gender.M  : Gender.F;
            Student student = new Student(id,name,gender,age);
            studentRepository.save(student);
        }
        context.setAttribute("studentRepository", studentRepository);
    }
}
