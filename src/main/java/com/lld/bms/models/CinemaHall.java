package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "CINEMA_HALLS")
public class CinemaHall extends AuditTable {
    private String hallNumber;
    private Integer seatCount;

    @ManyToOne
    private Cinema cinema;

    @OneToMany(mappedBy = "cinemaHall")
    private List<HallSeat> hallSeats = new ArrayList<>();
    @OneToMany(mappedBy = "cinemaHall")
    private List<Show> shows = new ArrayList<>();
}
