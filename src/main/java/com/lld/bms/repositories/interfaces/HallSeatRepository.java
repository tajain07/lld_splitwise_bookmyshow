package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.HallSeat;

public interface HallSeatRepository extends JpaRepository<HallSeat, Long> {
}
