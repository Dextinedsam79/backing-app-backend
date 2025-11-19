package com.sol.bankapp.dto;

public class AccountDto 
{
    private Long accountNumber;
    private String accountName;
    private Double accountBalance;

    // Constructor to FIX your error
    public AccountDto(Long accountNumber, String accountName, Double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public Double getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "AccountDto [accountNumber=" + accountNumber + ", accountName=" + accountName + ", accountBalance="
                + accountBalance + "]";
    }
}
