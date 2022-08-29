package com.digital.republic.donus.model;

import com.digital.republic.donus.domain.Users;
import com.digital.republic.donus.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUser(){
        Users user = new Users();
        user.setName("João");
        user.setCpf("234.233.345-99");
        userRepository.save(user);
        Integer countUsers = userRepository.findAll().size();
        assertEquals(1, countUsers);
    }

    @Test
    public void checkUserSaveWithCpf(){
        Users user = new Users();
        String cpf = "234.233.345-99";
        user.setName("João");
        user.setCpf(cpf);
        userRepository.save(user);
        Integer countUsers = userRepository.findAll().size();
        assertEquals(1, countUsers);
        Optional<Users> users = userRepository.findUserByCpf(cpf);

        assertNotNull(users);
        assertEquals(user, users.get());
    }
}
