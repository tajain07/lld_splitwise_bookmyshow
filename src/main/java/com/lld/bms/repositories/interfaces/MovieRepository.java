package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String movieName);
}
