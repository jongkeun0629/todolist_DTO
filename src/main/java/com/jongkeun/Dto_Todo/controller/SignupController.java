package com.jongkeun.Dto_Todo.controller;

import com.jongkeun.Dto_Todo.dto.SignupDto;
import com.jongkeun.Dto_Todo.model.User;
import com.jongkeun.Dto_Todo.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// UserController DB 방향
// Signup/Login Controller Service 방향
@Controller
@RequiredArgsConstructor
public class SignupController {
    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignup(Model model){
        model.addAttribute("signupDto", new SignupDto());

        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(
            // 검증절차. 서버로 넘길 때
            @Valid @ModelAttribute("signupDto") SignupDto signupDTO,
            // 검사 결과를 받아옴. 에러 포함
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()){
            return "signup";
        }

        // 중복 가입 여부 체크. username 유니크 값이여서 이미 중복 가입 불가. 사용자에게 알려주기 위한 로직


        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(signupDTO.getPassword())
                .build();
        userRepository.save(user);

        // registered: 쿼리 스트링. 들어온 값이 있으면 해당 페이지 띄우기
        return "redirect:/login?registered";    // http://localhost:8080/login?registered=true
    }
}
