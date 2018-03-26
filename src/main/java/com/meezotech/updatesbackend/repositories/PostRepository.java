package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findByGroup_Deleted(Pageable pageable, boolean deleted);
    Page<Post> findByGroupId(Pageable pageable, Long id);
    Page<Post> findByUserId(Pageable pageable, Long id);
}
