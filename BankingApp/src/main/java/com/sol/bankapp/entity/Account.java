package com.sol.bankapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bank_accounts")
public class Account 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_number")
	private Long accountNumber;
	@Column(name="account_name")
	private String accountName;
	@Column(name="account_balance")
	private Double accountBalance;
	public Account(Long accountNumber, String accountName, Double accountBalance) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
	}
	public Account() {
		super();
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

}
