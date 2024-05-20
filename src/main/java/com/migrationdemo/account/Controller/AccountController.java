package com.migrationdemo.account.Controller;

import com.migrationdemo.account.DTOs.AccountEntityDto;
import com.migrationdemo.account.Service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @QueryMapping
    List<AccountEntityDto> all() {
        return accountService.getAllAccounts();
    }
}