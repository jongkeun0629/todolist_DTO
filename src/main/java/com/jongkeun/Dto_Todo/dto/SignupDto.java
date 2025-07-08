package com.jongkeun.Dto_Todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    @NotBlank(message = "아이디를 입력하세요")
    @Size(min = 3, max = 10, message = "아이디는 3자에서 10자 이내로 작성해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8자에서 20자 이내로 작성해주세요")
    private String password;
}
