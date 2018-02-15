package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Page<Comment> findByPostId(Pageable pageable, Long id);
}
