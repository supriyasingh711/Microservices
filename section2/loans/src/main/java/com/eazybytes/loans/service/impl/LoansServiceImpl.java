package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
        if(loans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists for mobile number "+mobileNumber);

        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans loans = new Loans();
        loans.setMobileNumber(mobileNumber);
        long randomNumber = new Random().nextInt(900000000) + 10000000000L;
        loans.setLoanNumber(String.valueOf(randomNumber));
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return loans;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans,new LoansDto());

    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loan=loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Loan","loanNumber",loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto,loan);
        loansRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
        loansRepository.deleteById(loan.getLoanId());
        return true;
    }
}
