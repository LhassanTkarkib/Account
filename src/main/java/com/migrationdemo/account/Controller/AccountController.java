package com.migrationdemo.account.Controller;

import com.migrationdemo.account.DTOs.AccountEntityDto;
import com.migrationdemo.account.Mapper.AccountEntityMapper;
import com.migrationdemo.account.Service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @QueryMapping
    Iterable <AccountEntityDto> accounts() {
        return accountService.getAllAccounts();
    }

    @MutationMapping
    AccountEntityDto createAccount(@Argument long userId ,@Argument String accountNumber, @Argument Float balance) {
        AccountEntityDto accountEntityDto = new AccountEntityDto();
        accountEntityDto.setAccountNumber(accountNumber);
        accountEntityDto.setBalance(balance);
        return accountEntityMapper.toDto(accountService.createAccount(accountEntityMapper.toEntity(accountEntityDto)));
    }



}