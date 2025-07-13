package com.eazybytes.loans.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "Mobile Number should not be empty")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number should not be empty")
    private String loanNumber;

    @NotEmpty(message = "Loan Type should not be empty")
    private String loanType;

    @Positive(message = "Total Loan should greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Amount Paid should be greater than or equal to zero")
    private int amountPaid;

    @PositiveOrZero(message = "Amount Paid should be greater than or equal to zero")
    private int outstandingAmount;
}
