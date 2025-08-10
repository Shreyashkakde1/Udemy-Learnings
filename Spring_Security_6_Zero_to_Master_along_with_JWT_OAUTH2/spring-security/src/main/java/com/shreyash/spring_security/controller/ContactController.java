package com.shreyash.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/myContact")
    public String contactDetails() {
        return "Hear are the contact details from the DB";
    }
}
