package com.digital.republic.donus.repository;

import com.digital.republic.donus.domain.Account;
import com.digital.republic.donus.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByUser(Users user);

}
