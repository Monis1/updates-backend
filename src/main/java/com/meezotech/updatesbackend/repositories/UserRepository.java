package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
}
