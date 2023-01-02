package com.lld.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ADMINS")
public class Admin extends AuditTable {
    private String designation;
    private String phone1, phone2, phone3;
    private Long numberOfYearsInTheCompany;

    @OneToOne
    private User user;
}
