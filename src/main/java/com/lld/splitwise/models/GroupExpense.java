package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SPLITWISE_GROUP_EXPENSES")
public class GroupExpense extends BaseModel {

    // GroupExpense : Group
    // 1 : 1
    // M : 1
    @ManyToOne
    private Group group;

    // GroupExpense : Expense
    // 1 : 1
    // 1 : 1
    @OneToOne
    private Expense expense;
}
