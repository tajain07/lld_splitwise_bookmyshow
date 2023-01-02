package com.lld.splitwise.models;

import com.lld.splitwise.dtos.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Transaction {
    private UserDTO from;
    private UserDTO to;
    private int amount;
}
