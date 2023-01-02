package com.lld.splitwise.strategies.settleup;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;
import org.springframework.stereotype.Service;

import com.lld.bms.models.User;
import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.SplitwiseUser;
import com.lld.splitwise.models.Transaction;

import jakarta.persistence.Query;

@Service
public class GreedySettleUpExpensesStrategy implements SettleUpExpnsesStartegy {

    class Record {
        SplitwiseUser user;
        int pendingAmount;

        Record(SplitwiseUser user, int amount) {
            this.user = user;
            this.pendingAmount = amount;
        }
    }

    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        HashMap<SplitwiseUser, Integer> extraMoneyLeft = new HashMap<>();
        for (Expense expense : expenses) {
            Map<SplitwiseUser, Integer> owedBy = expense.getOwedBy();
            Map<SplitwiseUser, Integer> paidBy = expense.getPaidBy();

            for (SplitwiseUser paidByUser : paidBy.keySet()) {
                Integer currentAmount = extraMoneyLeft.getOrDefault(paidByUser, 0);
                Integer amountPaid = paidBy.get(paidByUser);

                extraMoneyLeft.put(paidByUser, amountPaid + currentAmount);
            }

            for (SplitwiseUser owedByUser : owedBy.keySet()) {
                Integer currentAmount = extraMoneyLeft.getOrDefault(owedByUser, 0);
                Integer amountPaid = paidBy.get(owedByUser);

                extraMoneyLeft.put(owedByUser, amountPaid - currentAmount);
            }
        }

        Queue<Record> negativUsers = new ArrayDeque<>();
        Queue<Record> positiveUsers = new ArrayDeque<>();

        List<Transaction> transactions = new ArrayList<>();

        for (SplitwiseUser user : extraMoneyLeft.keySet()) {
            Integer amount = extraMoneyLeft.get(user);
            if (amount > 0) {
                positiveUsers.add(new Record(user, amount));
            } else if (amount < 0) {
                negativUsers.add(new Record(user, amount));
            }
        }

        while (!positiveUsers.isEmpty() && !negativUsers.isEmpty()) {
            Record currentNegativeUser = negativUsers.remove();
            Record currentPositiveUser = positiveUsers.remove();

            if (currentPositiveUser.pendingAmount > Math.abs(currentNegativeUser.pendingAmount)) {
                transactions.add(new Transaction(currentNegativeUser.user.toDTO(), currentPositiveUser.user.toDTO(),
                        Math.abs(currentNegativeUser.pendingAmount)));

                positiveUsers.add(new Record(currentPositiveUser.user,
                        currentPositiveUser.pendingAmount - Math.abs(currentNegativeUser.pendingAmount)));
            } else {
                transactions.add(new Transaction(currentNegativeUser.user.toDTO(), currentPositiveUser.user.toDTO(),
                        currentPositiveUser.pendingAmount));

                negativUsers.add(new Record(currentNegativeUser.user,
                        currentNegativeUser.pendingAmount + currentPositiveUser.pendingAmount));
            }

        }

        return transactions;
    }

}
