package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Admin;
import com.lld.bms.models.User;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findAllByUserAndPhone1AndPhone2(User user, String phone1, String phone2);
}
