package com.lld.splitwise.dtos;

import java.util.List;

import com.lld.splitwise.models.Transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpResponseDTO {
    List<Transaction> transactions;
}
