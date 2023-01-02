package com.lld.bms.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bms.models.Booking;
import com.lld.bms.models.Customer;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getBookingsByCustomer(Customer customer);
}