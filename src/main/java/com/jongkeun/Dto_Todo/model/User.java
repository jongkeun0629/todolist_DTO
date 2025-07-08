package com.jongkeun.Dto_Todo.model;

import lombok.Builder;
import lombok.Data;

// 빈 생성자, 매개변수 있는 생성자, getter/setter, toString 등
@Data
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
}
