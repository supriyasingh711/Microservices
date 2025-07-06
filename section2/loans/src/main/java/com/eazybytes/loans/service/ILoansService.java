package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;


public interface ILoansService {

    /**
     * This method is used to create a new loan account.
     *
     * @param mobileNumber the mobile number of the customer
     */
    void createLoan(String mobileNumber);

    /**
     * This method is used to fetch a loan account by mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return a LoansDto object
     */
    LoansDto fetchLoan(String mobileNumber);
    /**
     * This method is used to update an existing loan account.
     *
     * @param loansDto the LoansDto object containing updated loan details
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     * This method is used to delete a loan account.
     *
     * @param mobileNumber the mobile number of the customer
     * @return true if the loan account is deleted successfully
     */
    boolean deleteLoan(String mobileNumber);
}
