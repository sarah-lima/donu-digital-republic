package com.digital.republic.donus.controller;

import com.digital.republic.donus.domain.Account;
import com.digital.republic.donus.dto.DepositDto;
import com.digital.republic.donus.dto.TransferenceDto;
import com.digital.republic.donus.repository.AccountRepository;
import com.digital.republic.donus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestBody DepositDto depositDto){
        Optional<Account> account = accountRepository.findById(depositDto.getAccountId());
        if(account.isPresent()){
            if(depositDto.getNewValue() <= 2000){
                account.get().setBalance(depositDto.getNewValue());
                accountRepository.save(account.get());
                return ResponseEntity.status(201).body(account);
            }
            return ResponseEntity.status(400).body("Não é possível fazer o depósito de valores maiores que R$2000,00");
        }
        return ResponseEntity.status(401).body("Conta não encontrada");
    }

    @PostMapping("/transference")
    public ResponseEntity transference(@RequestBody TransferenceDto transferenceDto){
        Optional<Account> account = accountRepository.findById(transferenceDto.getUserAccount());
        Optional<Account> accountDeposit = accountRepository.findById(transferenceDto.getDepositAccount());
        if(account.get().getBalance() > transferenceDto.getValueDeposit()){
            double newValue = account.get().getBalance() - transferenceDto.getValueDeposit();
            account.get().setBalance(newValue);
            accountDeposit.get().setBalance(transferenceDto.getValueDeposit());
            accountRepository.save(account.get());
            accountRepository.save(accountDeposit.get());
            return ResponseEntity.status(201).body(transferenceDto);
        }
        return ResponseEntity.status(400).body("Saldo insuficiente para transferência");
    }
}
