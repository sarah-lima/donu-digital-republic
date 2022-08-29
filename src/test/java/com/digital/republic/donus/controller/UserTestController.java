package com.digital.republic.donus.controller;

import com.digital.republic.donus.domain.Account;
import com.digital.republic.donus.domain.Users;
import com.digital.republic.donus.repository.AccountRepository;
import com.digital.republic.donus.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserTestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AccountRepository accountRepository;


    @Test
    public void findAllUsers() throws Exception {
        Users user = new Users();
        user.setName("João");
        user.setCpf("234.233.345-99");
        List<Users> userList = List.of(user);
        when(userRepository.findAll()).thenReturn(userList);
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("234.233.345-99")));
    }

    @Test
    public void createUser() throws Exception {
        this.mockMvc.perform(post("/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Rebeca Carvalho\",\n" +
                                "    \"cpf\": \"427.557.238-67\"\n" +
                                "}"))
                .andExpect(status().isCreated());
    }

}
