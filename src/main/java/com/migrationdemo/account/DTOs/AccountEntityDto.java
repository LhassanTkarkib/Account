package com.migrationdemo.account.DTOs;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.migrationdemo.account.Entity.AccountEntity}
 */
@Value
public class AccountEntityDto implements Serializable {
    Long id;
    String accountNumber;
    double balance;
    LocalDate createdDate;
}