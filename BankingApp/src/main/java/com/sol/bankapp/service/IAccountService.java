package com.sol.bankapp.service;


import java.util.List;

import com.sol.bankapp.dto.AccountDto;

public interface IAccountService {
	AccountDto openAccount(AccountDto accountDto);
	AccountDto getAccountByAcctNumber(Long accountNumber);
	AccountDto deposit(Long accountNumber, Double depositAmt);
	AccountDto withdraw(Long accountNumber, Double withdrawAmt);
	List<AccountDto>getAllAccount();
	void deleteAccountByAccountNumber(Long accountNumber);


}
