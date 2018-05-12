package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.GroupAdmin;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupAdminRepository extends CrudRepository<GroupAdmin, Long> {

    Optional<GroupAdmin> findByGroupIdAndPassword(Long groupId, String password);

}
