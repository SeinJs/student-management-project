package com.nhnacademy.student.login.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "userId is empty")
    private String userId;
    @NotBlank(message = "userPassword is empty")
    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
