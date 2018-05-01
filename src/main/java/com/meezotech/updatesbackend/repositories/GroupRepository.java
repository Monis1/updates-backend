package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Group;
import com.meezotech.updatesbackend.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {

    Group findByIdAndBannedUsers(Long id, User user);

}
