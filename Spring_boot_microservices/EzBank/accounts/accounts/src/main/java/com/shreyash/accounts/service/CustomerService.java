package com.shreyash.accounts.service;

import com.shreyash.accounts.dto.CustomerDetailsDto;

public interface CustomerService {
    /**
     * Fetches the details of a customer based on their mobile number.
     *
     * @param mobileNumber the mobile number of the customer, which must be 10 digits
     * @return CustomerDetailsDto containing the customer's details
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
