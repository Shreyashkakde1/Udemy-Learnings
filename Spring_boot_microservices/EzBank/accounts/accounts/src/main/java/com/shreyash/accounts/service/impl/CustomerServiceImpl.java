package com.shreyash.accounts.service.impl;


import com.netflix.discovery.converters.Auto;
import com.shreyash.accounts.dto.AccountsDto;
import com.shreyash.accounts.dto.CardsDto;
import com.shreyash.accounts.dto.CustomerDetailsDto;
import com.shreyash.accounts.dto.LoansDto;
import com.shreyash.accounts.entity.Accounts;
import com.shreyash.accounts.entity.Customer;
import com.shreyash.accounts.exception.ResourceNotFoundException;
import com.shreyash.accounts.mapper.AccountsMapper;
import com.shreyash.accounts.mapper.CustomerMapper;
import com.shreyash.accounts.repository.AccountsRepository;
import com.shreyash.accounts.repository.CustomerRepository;
import com.shreyash.accounts.service.CustomerService;
import com.shreyash.accounts.service.client.LoansFeingClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shreyash.accounts.service.client.CardFeingClient;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardFeingClient cardsFeignClient;
    private LoansFeingClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Customer","mobleNumber",mobileNumber)
                );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Accounts","customerId",customer.getCustomerId().toString())
                );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());


        return customerDetailsDto;
    }
}
