package com.lld.splitwise.models;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SPLITWISE_EXPENSES")
public class Expense extends BaseModel {
    private int amount;

    // created_by
    // expense : user
    // 1 : 1
    // m : 1
    // curent class to attribute class
    @ManyToOne
    private SplitwiseUser createdBy;
    private String description;

    @ElementCollection
    private Map<SplitwiseUser, Integer> paidBy;

    @ElementCollection
    private Map<SplitwiseUser, Integer> owedBy;

}
