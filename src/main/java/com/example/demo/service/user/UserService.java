package com.example.demo.service.user;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService {
    User findByEmail(String email);

    UserDTO save(UserDTO userDTO);
}
