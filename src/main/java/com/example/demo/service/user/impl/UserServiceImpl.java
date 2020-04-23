package com.example.demo.service.user.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.util.Constants.ACTIVE;
import static com.example.demo.util.Constants.ROLE_USER;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getFullname());
        user.setStatus(ACTIVE);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> authorities = new HashSet<>();
        roleRepository.findByName(ROLE_USER).ifPresent(authorities::add);
        user.setRoles(authorities);
//        user.setRoles(Arrays.asList(new Role("USER", "USER")));
        this.userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable)
                .stream().parallel()
                .map(el -> mapper.map(el, UserDTO.class))
                .collect(Collectors.toList());
    }
}
