package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
