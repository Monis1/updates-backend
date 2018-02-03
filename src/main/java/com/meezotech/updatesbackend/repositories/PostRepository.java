package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
