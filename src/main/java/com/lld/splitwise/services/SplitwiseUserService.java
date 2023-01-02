package com.lld.splitwise.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lld.bms.models.User;
import com.lld.bms.repositories.interfaces.UserRepository;
import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.SplitwiseUser;
import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.repositories.ExpenseRepository;
import com.lld.splitwise.repositories.SplitwiseUserRepository;
import com.lld.splitwise.services.password_hashing.PasswordHashingStrategy;
import com.lld.splitwise.strategies.settleup.SettleUpExpnsesStartegy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SplitwiseUserService {

    private PasswordHashingStrategy passwordHashingStrategy;
    private SplitwiseUserRepository splitwiseUserRepository;
    private final ExpenseRepository expenseRepository;
    private final SettleUpExpnsesStartegy settleUpExpnsesStartegy;

    // Better have a difference service with strategy
    // private String hashPassword(String originalPassword) {
    // return originalPassword + "hashed";
    // }

    public SplitwiseUser registerUser(String name, String password, String phoneNumber) {

        // hash the password
        String hashedPassword = passwordHashingStrategy.hash(password);

        // create the user object --> no ID
        SplitwiseUser user = SplitwiseUser.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .hashedPassword(hashedPassword)
                .build();

        // save the object
        SplitwiseUser savedUser = splitwiseUserRepository.save(user);
        return savedUser;
    }

    public List<Transaction> settleUp(Long userId) {

        SplitwiseUser user = splitwiseUserRepository.getUserById(userId).get();

        // 1. Get all the expnses that the user is a part of
        List<Expense> expenses = expenseRepository
                .findAllByPaidByContainingOrOwedByContaining(userId, userId);
        // 2. Run the algo
        List<Transaction> transactions = settleUpExpnsesStartegy.settle(expenses);
        // 3. Filter the transactions where the from or to

        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getFrom().getPhoneNumber().equals(user.getPhoneNumber()) ||
                    transaction.getTo().getPhoneNumber().equals(user.getPhoneNumber())) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
}
// never tit