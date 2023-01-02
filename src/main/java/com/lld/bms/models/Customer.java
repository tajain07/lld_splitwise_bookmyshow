package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "CUSTOMERS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AuditTable {
    private String fullName;
    private String phone;
    private String city;
    private String email;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "customer")
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();
}
