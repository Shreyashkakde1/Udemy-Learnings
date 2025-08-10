package com.shreyash.accounts.service;

import com.shreyash.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountsService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
