package com.sol.bankapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sol.bankapp.dto.AccountDto;
import com.sol.bankapp.dto.AccountMapper;
import com.sol.bankapp.entity.Account;
import com.sol.bankapp.repo.AccountRepository;
@Service
public class AccountService implements IAccountService 
{
	@Autowired
private AccountRepository acctRepo;
	@Override
	public AccountDto openAccount(AccountDto accountDto) 
	{
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = acctRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	@Override
	public AccountDto getAccountByAcctNumber(Long accountNumber)
	{
		Account account = acctRepo.findById(accountNumber).orElseThrow(()->new RuntimeException("Account number does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}
	@Override
	public AccountDto deposit(Long accountNumber, Double depositAmt) {
		Account account = acctRepo.findById(accountNumber).orElseThrow(()->new RuntimeException("Account number does not exist"));
		double currentBalance = account.getAccountBalance() + depositAmt;
		account.setAccountBalance(currentBalance);
		return AccountMapper.mapToAccountDto(acctRepo.save(account));
	}
	@Override
	public AccountDto withdraw(Long accountNumber, Double withdrawAmt) {

	    // 1. Validate withdrawal amount first
	    if (withdrawAmt == null || withdrawAmt <= 0) {
	        throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
	    }

	    // 2. Fetch the account
	    Account account = acctRepo.findById(accountNumber)
	            .orElseThrow(() -> new RuntimeException("Account number does not exist"));

	    double accBalance = account.getAccountBalance();

	    

	    // 3. Check sufficient balance
	    if (accBalance <= 0) {
	        throw new RuntimeException("Insufficient funds. Account balance is zero.");
	    }

	    // 4. Prevent overdraft
	    if (withdrawAmt > accBalance) {
	        throw new RuntimeException("Insufficient funds. Withdrawal amount exceeds account balance.");
	    }

	    // 5. enforce minimum balance rule
	    double minimumRequiredBalance = 100.00;  // example rule
	    if (accBalance - withdrawAmt < minimumRequiredBalance) {
	        throw new RuntimeException(
	                "Transaction denied. Account must maintain a minimum balance of " + minimumRequiredBalance);
	    }

	    // 6. Process withdrawal
	    double currentBalance = accBalance - withdrawAmt;
	    account.setAccountBalance(currentBalance);

	    // 7. Save and return DTO
	    return AccountMapper.mapToAccountDto(acctRepo.save(account));
	}
	@Override
	public List<AccountDto> getAllAccount() {
		List<Account> accountList = acctRepo.findAll();
		return accountList.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
	}
	@Override
	public void deleteAccountByAccountNumber(Long accountNumber) {
		 Account account = acctRepo.findById(accountNumber)
		            .orElseThrow(() -> new RuntimeException("Account number does not exist."));

		    acctRepo.delete(account);		
	}


}
