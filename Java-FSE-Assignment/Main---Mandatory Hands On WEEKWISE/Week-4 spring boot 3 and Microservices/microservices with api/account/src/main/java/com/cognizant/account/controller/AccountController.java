package com.cognizant.account.controller;

import org.springframework.web.bind.annotation.*;

import com.cognizant.account.model.Account;

@RestController
public class AccountController {

    @GetMapping("/accounts/{number}")
    public Account getAccount(@PathVariable String number) {

        return new Account(
                number,
                "Savings",
                234343
        );
    }
}
