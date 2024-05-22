package com.migrationdemo.account.DTOs;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AccountEntityDto implements Serializable {
    Long id;
    String accountNumber;
    double balance;
    Long UserId;

}