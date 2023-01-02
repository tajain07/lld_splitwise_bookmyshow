package com.lld.bms.services;

import org.springframework.stereotype.Service;

import com.lld.bms.dtos.CreateUserDTO;
import com.lld.bms.models.Role;
import com.lld.bms.models.User;
import com.lld.bms.repositories.interfaces.UserRepository;
import com.lld.bms.services.password.PasswordEncoder;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;

    public User createUser(CreateUserDTO userRequest) {
        User user = new User(userRequest.getUsername());
        user.setPassword(userRequest.getPassword(), passwordEncoder);

        Role role = roleService.getRole(userRequest.getRoleName());
        user.addRole(role);

        return userRepository.save(user);

    }

    public boolean doesUserExist(String username, String email) {

        if (userRepository.findUserByUsername(username).isPresent()) {
            return true;
        }

        if (userRepository.findUserByEmail(email).isPresent()) {
            return true;
        }

        return false;

    }
}
