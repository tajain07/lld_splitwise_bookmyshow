package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "MOVIES")
public class Movie extends AuditTable {
    private String name;
    private Integer durationMinutes;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows = new ArrayList<>();
}
