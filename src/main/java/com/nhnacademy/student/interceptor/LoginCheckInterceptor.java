package com.nhnacademy.student.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final Set<String> excludeUrls = new HashSet<>();

    public LoginCheckInterceptor() {
        excludeUrls.add("/login");
        excludeUrls.add("/login/");
        excludeUrls.add("/login/logout");
        excludeUrls.add("/resources/");
        excludeUrls.add("/favicon.ico");
        excludeUrls.add("/api/");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        log.debug("login check filtering path: {}", path);
        if (checkUrl(path)) {
            HttpSession session = request.getSession(false);
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
                response.sendRedirect("/login/");
                return false;
            }
        }
        return true;
    }

    private boolean checkUrl(String path) {
        for (String excludeUrl : excludeUrls) {
            if (path.contains(excludeUrl)) {
                return false;
            }
        }
        return true;
    }
}
