package com.nhnacademy.student.common;

import com.nhnacademy.student.domain.Gender;

public class TagUtils {
    public String gender(Gender gender){
        if (gender.name().equals("M")){
            return "남성";
        } else if (gender.name().equals("F")) {
            return "여성";
        }else {
            return "";
        }
    }
}
