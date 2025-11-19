package com.sol.bankapp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sol.bankapp.dto.AccountDto;
import com.sol.bankapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController 
{
	@Autowired
	private AccountService acctService;
	@PostMapping
	public ResponseEntity<AccountDto> openAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(acctService.openAccount(accountDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountDto>getAccountByAccountNumber(@PathVariable Long accountNumber){
		AccountDto acctRetrieved = acctService.getAccountByAcctNumber(accountNumber);
		return ResponseEntity.ok(acctRetrieved);
	}
	@PutMapping("/{accountNumber}/deposit")
	public ResponseEntity<AccountDto>deposit(@PathVariable Long accountNumber, @RequestBody Map<String, Double> req)
	{
		AccountDto acctDto = acctService.deposit(accountNumber, req.get("depositAmt"));
		return ResponseEntity.ok(acctDto);
	}
	
	@PostMapping("withdraw/{accountNumber}")
	public ResponseEntity<?>withdraw(@PathVariable Long accountNumber, @RequestBody Map<String, Double> req){
		if(!req.containsKey("withdrawAmt"))
		{
			return ResponseEntity.badRequest().body("Missing field: withdrawAmt");
		}
		Double amt = req.get("withdrawAmt");
		if(amt == null || amt <=0) {
			return ResponseEntity.badRequest().body("Withdrawal amount must be greater than zero");
		}
		
		return ResponseEntity.ok(acctService.withdraw(accountNumber, amt));
		
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>>getAllAccount(){
		return ResponseEntity.ok(acctService.getAllAccount());
	}
	
	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable Long accountNumber) {

	    try {
	        acctService.deleteAccountByAccountNumber(accountNumber);
	        return ResponseEntity.ok("Account deleted successfully.");
	    } 
	    catch (RuntimeException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
}
