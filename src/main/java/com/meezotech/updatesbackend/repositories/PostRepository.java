package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    Page<Post> findByGroup_DeletedAndApproved(Pageable pageable, boolean deleted, boolean isApproved);

    Page<Post> findByGroupIdAndGroup_DeletedAndApproved(Pageable pageable, Long id, boolean deleted, boolean approved);

    Page<Post> findByUserIdAndGroup_DeletedAndApproved(Pageable pageable, Long id, boolean deleted, boolean approved);

    Long countAllByDateGreaterThanEqualAndDateLessThanEqual(Date startDate, Date endDate);

}
