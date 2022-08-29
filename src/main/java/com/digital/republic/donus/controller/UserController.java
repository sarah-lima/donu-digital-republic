package com.digital.republic.donus.controller;

import com.digital.republic.donus.domain.Account;
import com.digital.republic.donus.domain.Users;
import com.digital.republic.donus.repository.AccountRepository;
import com.digital.republic.donus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity getUser(){
        try{
            return ResponseEntity.ok().body(userRepository.findAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody Users user){
        try{
            if(userRepository.findUserByCpf(user.getCpf()).isPresent()){
                return ResponseEntity.status(200).body("Usuário já possui conta");
            }
            Users userId = userRepository.save(user);
            Account account = new Account();
            account.setUser(userId);
            accountRepository.save(account);
            return ResponseEntity.status(201).body(userId);
        }catch(Exception e){
            return ResponseEntity.status(404).body("Erro ao cadastrar usuário!");
        }

    }
}
