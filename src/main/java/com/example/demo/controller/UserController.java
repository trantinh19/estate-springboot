package com.example.demo.controller;

import com.example.demo.config.security.annotation.IsAdmin;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.request.UserInfoRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.user.UserService;
import com.example.demo.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registrationUserAccount(@RequestBody UserDTO userDTO) {
        User existing = this.userService.findByEmail(userDTO.getEmail());
        if(existing != null) throw new RuntimeException(" Email was existed");

        UserDTO user = this.userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/profile")
    @IsAdmin
    public PageResponse<UserDTO> findAll(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                         @RequestParam(value = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<User> response = this.userRepository.findAll(pageable);
        List<UserDTO> result = this.userService.findAll(pageable);
        return new PageResponse<>(result, response.getTotalPages(), response.getTotalElements(), page, size);
    }

    @PutMapping("/profile")
    @IsAdmin
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UserInfoRequest request) {
        User oldUser = this.userService.findByEmail(request.getEmail());
        if(oldUser != null) {
            oldUser.setEmail(request.getEmail());
            oldUser.setUsername(request.getUsername());
            oldUser.setFullname(request.getFullname());
            this.userService.save(oldUser);
        }
        return ResponseEntity.ok(oldUser);
    }
}
