package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
