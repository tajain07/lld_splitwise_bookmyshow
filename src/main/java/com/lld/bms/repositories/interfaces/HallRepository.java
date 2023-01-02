package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.CinemaHall;

public interface HallRepository extends JpaRepository<CinemaHall, Long> {
}
