package com.digital.republic.donus.controller;

import com.digital.republic.donus.domain.Account;
import com.digital.republic.donus.domain.Users;
import com.digital.republic.donus.repository.AccountRepository;
import com.digital.republic.donus.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class AccountTestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AccountRepository accountRepository;


    @Test
    public void depositNotAuthorization() throws Exception {
        this.mockMvc.perform(post("/account/deposit")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"accountId\": 1,\n" +
                                "    \"newValue\": 1000.0\n" +
                                "}"))
                .andExpect(status().is(401))
                .andExpect(content().string(containsString("Conta")));
    }
}
