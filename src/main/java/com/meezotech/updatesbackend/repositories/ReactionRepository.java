package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Reaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReactionRepository extends CrudRepository<Reaction, Long> {

    Reaction findByUserIdAndPostId(long userId, long postId);

}
