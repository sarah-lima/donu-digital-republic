package com.digital.republic.donus.repository;

import com.digital.republic.donus.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findUserByCpf(String cpf);

}
