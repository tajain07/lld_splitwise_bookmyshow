package com.lld.bms.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lld.bms.models.Role;
import com.lld.bms.repositories.interfaces.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        role.setDescription(roleName);
        role.setUpdated(LocalDateTime.now());
        return roleRepository.save(role);
    }

    public Role getRole(String roleName) {
        try {

            Optional<Role> roleByName = roleRepository.getRoleByName(roleName);
            if (roleByName.isPresent()) {
                Role role = roleByName.get();
                return role;

            } else {
                return createRole(roleName);
            }

        } catch (Exception e) {
        }
        return null;

    }

}
