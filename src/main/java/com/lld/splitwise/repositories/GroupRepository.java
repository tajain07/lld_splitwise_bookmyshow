package com.lld.splitwise.repositories;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.splitwise.models.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long id);
}
