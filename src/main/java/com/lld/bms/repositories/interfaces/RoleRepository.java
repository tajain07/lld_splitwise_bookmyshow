package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByName(String roleName);

}
