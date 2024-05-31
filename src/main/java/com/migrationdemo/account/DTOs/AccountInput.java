package com.migrationdemo.account.DTOs;

import lombok.Data;

import java.util.List;


@Data
public class AccountInput {
    private Long userId;
    private String accountNumber;
    private Float balance;
    private List<CardInput> cards;
}
