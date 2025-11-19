package com.sol.bankapp.dto;
import com.sol.bankapp.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto acctDto) 
	{
		Account acct = new Account(
				acctDto.getAccountNumber(),
				acctDto.getAccountName(),
				acctDto.getAccountBalance()
				);
				return acct;
		
	}
	
	public static AccountDto mapToAccountDto(Account account)
	{
		AccountDto accountDto = new AccountDto(
				account.getAccountNumber(),
				account.getAccountName(),
				account.getAccountBalance()
				);
		return accountDto;
	}

}
