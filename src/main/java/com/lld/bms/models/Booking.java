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
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "BOOKINGS")
public class Booking extends AuditTable {
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Show show;
    private BookingStatus status;

    @OneToMany(mappedBy = "booking")
    List<ShowSeat> seatsBooked = new ArrayList<>();
}
