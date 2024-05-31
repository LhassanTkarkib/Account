package com.migrationdemo.account.DTOs;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.migrationdemo.account.Entity.AccountEntity}
 */
@Data
public class AccountEntityDto implements Serializable {
    Long id;
    String accountNumber;
    double balance;
    Long UserId;
    List<CardsEntityDto> cards;
}