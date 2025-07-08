package com.jongkeun.Dto_Todo.controller;

import com.jongkeun.Dto_Todo.dto.LoginDto;
import com.jongkeun.Dto_Todo.model.User;
import com.jongkeun.Dto_Todo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;

    // 두 가지 경로
    @GetMapping({"/", "/login"})
    public String showLogin(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            // 검증
            @Valid @ModelAttribute("loginDto") LoginDto loginDto,
            // 검증 결과
            BindingResult bindingResult,
            // + 사용자 구분 위한 토큰 값 부여. 세션
            HttpSession httpSession,
            Model model
    ){
        if(bindingResult.hasErrors()){
            return "login";
        }

        try{
            User user = userRepository.findByUsername(loginDto.getUsername());

            // 유저의 비밀번호와 입력받은 비밀번호가 다를 경우
            if (!user.getPassword().equals(loginDto.getPassword())){
                model.addAttribute("error", "비밀번호가 올바르지 않습니다.");

                return "login";
            }

            httpSession.setAttribute("user", user);

            return "redirect:/todos";
        } catch (Exception e) {
            model.addAttribute("error", "존재하지 않는 사용자입니다.");

            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        // 세션 회수
        session.invalidate();

        return "redirect:/login";
    }
}
