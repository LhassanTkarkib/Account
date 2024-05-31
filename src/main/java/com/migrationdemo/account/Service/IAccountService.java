package com.migrationdemo.account.Service;

import com.migrationdemo.account.DTOs.AccountEntityDto;
import com.migrationdemo.account.DTOs.AccountInput;
import com.migrationdemo.account.Entity.AccountEntity;

import java.util.List;

public interface IAccountService {
    AccountEntity getAccountById(Long id);

    AccountEntity createAccount(AccountInput accountInput);

    AccountEntity updateAccount(AccountEntity accountEntity);

    void deleteAccount(Long id);

    List<AccountEntityDto> getAllAccounts();
}
