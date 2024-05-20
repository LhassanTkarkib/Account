package com.migrationdemo.account.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/account")
public String GenrateAccountNumber() {
    return "Account Number is 123456789";
}

}
