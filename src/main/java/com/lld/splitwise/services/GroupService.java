package com.lld.splitwise.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.Group;
import com.lld.splitwise.models.GroupExpense;
import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.repositories.GroupExpenseRepository;
import com.lld.splitwise.repositories.GroupRepository;
import com.lld.splitwise.strategies.settleup.SettleUpExpnsesStartegy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupExpenseRepository groupExpenseRepository;
    private final SettleUpExpnsesStartegy settleUpExpnsesStartegy;

    public List<Transaction> settleUp(Long groupId) {

        Group group = groupRepository.findById(groupId).get();

        // 1. Get all the expsnses of the group.
        List<GroupExpense> groupExpenses = groupExpenseRepository.findAllByGroup(group);
        List<Expense> expenses = new ArrayList<>();

        for (GroupExpense groupExpense : groupExpenses) {
            expenses.add(groupExpense.getExpense());
        }

        // 2. Call the algo that takes a list of expenses and returns a list of
        // transactions to settle up those expenses.
        List<Transaction> settleTransactions = settleUpExpnsesStartegy.settle(expenses);
        return settleTransactions;
    }
}
