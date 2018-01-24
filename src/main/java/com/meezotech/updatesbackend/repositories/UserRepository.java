package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
