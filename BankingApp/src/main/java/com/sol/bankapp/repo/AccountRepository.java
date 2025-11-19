package com.sol.bankapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sol.bankapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>
{

}
