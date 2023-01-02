package com.lld.splitwise.strategies.settleup;

import java.util.List;

import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.Transaction;

public interface SettleUpExpnsesStartegy {
    List<Transaction> settle(List<Expense> expenses);
}
