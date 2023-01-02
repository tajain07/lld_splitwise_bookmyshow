package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
