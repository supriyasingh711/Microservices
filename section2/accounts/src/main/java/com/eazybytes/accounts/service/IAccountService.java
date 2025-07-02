package com.eazybytes.accounts.service;


import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the data transfer object containing customer details
     */


    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
