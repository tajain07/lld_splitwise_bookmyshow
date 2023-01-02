package com.lld.splitwise.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SPLITWISE_GROUPS")
public class Group extends BaseModel {
    private String name;

    // group: admins
    // 1 : m
    // m : 1
    @ManyToMany
    private List<SplitwiseUser> admins;
    // group members
    // 1 m
    // m 1
    @ManyToMany
    private List<SplitwiseUser> members;

}
