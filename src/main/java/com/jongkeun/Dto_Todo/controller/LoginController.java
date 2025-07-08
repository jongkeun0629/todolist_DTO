package com.jongkeun.Dto_Todo.controller;

import com.jongkeun.Dto_Todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;

    // 둘 다
    @GetMapping({"/", "/login"})
    public String showLogin(){
        return "login";
    }
}
