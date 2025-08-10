package com.shreyash.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String balanceDetails() {
        return "Hear are the balance details from the DB";
    }
}
