package com.nhnacademy.student.login.web;

import com.nhnacademy.student.controller.BaseController;
import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.login.request.LoginRequest;
import com.nhnacademy.student.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController implements BaseController {
    private final LoginService loginService;
    private final User user;

    public LoginController(LoginService loginService, User user) {
        this.loginService = loginService;
        this.user = user;
    }

    @GetMapping(value = {"/",""})
    public String loginForm(Model model, User user){
        if(Objects.nonNull(user)){
            return "redirect:/";
        }
        log.info("message:{}", model.getAttribute("message"));
        model.addAttribute("loginRequest",new LoginRequest());
        return "login/loginForm";
    }

    @PostMapping(value = {"/",""})
    public String doLogin(@Valid LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("loginRequest", loginRequest);
            return "login/loginForm";
        }

        if( loginService.match(user, loginRequest) ){
            log.info("session:check");
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("message", "로그인 실패");
        return "redirect:/login/";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(Objects.nonNull(session)){
            session.invalidate();
            Cookie cookie = new Cookie("JSESSIONID","");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/login/";
    }
}
