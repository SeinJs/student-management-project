package com.nhnacademy.student.login.service;

import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.login.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean match(User user, LoginRequest loginRequest){
        return user.getUserId().equals(loginRequest.getUserId()) && user.getUserPassword().equals(loginRequest.getUserPassword());
    }
}
