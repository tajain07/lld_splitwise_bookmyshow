package com.lld.splitwise.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lld.splitwise.models.SplitwiseUser;

public interface SplitwiseUserRepository extends JpaRepository<SplitwiseUser, Long> {
    SplitwiseUser save(SplitwiseUser user);

    Optional<SplitwiseUser> getUserById(Long userId);
}
