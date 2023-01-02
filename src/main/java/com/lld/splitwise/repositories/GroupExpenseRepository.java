package com.lld.splitwise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.splitwise.models.Group;
import com.lld.splitwise.models.GroupExpense;

public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {

    List<GroupExpense> findAllByGroup(Group group);
}
