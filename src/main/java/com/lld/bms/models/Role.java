package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ROLES")
public class Role extends AuditTable {
    private String name;
    private String description;

    public Role() {

    }
}
