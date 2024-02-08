package com.nhnacademy.student.student.request;

import com.nhnacademy.student.annotation.EnumPattern;
import com.nhnacademy.student.domain.Gender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class StudentModifyRequest {
    @NotBlank(message = "이름을 입력해주세요")
    private  String name;

    @EnumPattern(regexp = "M|F")
    private Gender gender;

    @Min(value = 20, message = "나이는 20살 이상입니다.")
    @Max(value = 30, message = "나이는 30살 미만입니다.")
    private  int age;

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "StudentModifyRequest{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
