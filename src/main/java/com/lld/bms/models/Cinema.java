package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CINEMAS")
@EqualsAndHashCode(callSuper = false)
public class Cinema extends AuditTable {
    private String name;
    private String address;

    @OneToMany(mappedBy = "cinema")
    private List<CinemaHall> halls = new ArrayList<>();
}
