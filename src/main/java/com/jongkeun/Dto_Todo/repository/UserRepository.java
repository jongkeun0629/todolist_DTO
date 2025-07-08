package com.jongkeun.Dto_Todo.repository;

import com.jongkeun.Dto_Todo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
// final 생성자
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public int save(User user){
        String sql = "INSERT INTO users (username, password) VALUES(?, ?)";

        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }
}
