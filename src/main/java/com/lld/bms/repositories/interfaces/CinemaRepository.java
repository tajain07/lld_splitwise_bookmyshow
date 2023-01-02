package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
