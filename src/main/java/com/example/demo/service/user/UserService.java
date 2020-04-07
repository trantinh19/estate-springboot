package com.example.demo.service.user;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    UserDTO createUser(UserDTO userDTO);

    User save(User user);

    List<UserDTO> findAll(Pageable pageable);
}
