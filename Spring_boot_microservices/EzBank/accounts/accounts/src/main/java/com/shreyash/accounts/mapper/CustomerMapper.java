package com.shreyash.accounts.mapper;

import com.shreyash.accounts.dto.CustomerDetailsDto;
import com.shreyash.accounts.dto.CustomerDto;
import com.shreyash.accounts.entity.Customer;

public class CustomerMapper {
    /**
     * Maps a Customer entity to a CustomerDto.
     *
     * @param customer the Customer entity to map from
     * @param customerDto the CustomerDto to map to
     * @return the mapped CustomerDto
     */
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    /**
     * Maps a CustomerDto to a Customer.
     *
     * @param customerDto the CustomerDto to map
     * @param customer the Customer to map to
     * @return the mapped Customer
     */
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

    /**
     * Maps a Customer entity to a CustomerDetailsDto.
     *
     * @param customer the Customer entity to map from
     * @param customerDetailsDto the CustomerDetailsDto to map to
     * @return the mapped CustomerDetailsDto
     */
    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto){
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        return customerDetailsDto;
    }
}
