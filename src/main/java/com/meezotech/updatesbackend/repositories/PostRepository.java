package com.meezotech.updatesbackend.repositories;

import com.meezotech.updatesbackend.api.v1.model.PostListDTO;
import com.meezotech.updatesbackend.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    Page<Post> findByGroup_DeletedAndApproved(Pageable pageable, boolean deleted, boolean isApproved);

    Page<Post> findByGroupIdAndGroup_DeletedAndApproved(Pageable pageable, Long id, boolean deleted, boolean approved);

    Page<Post> findByUserIdAndGroup_DeletedAndApproved(Pageable pageable, Long id, boolean deleted, boolean approved);

    Long countAllByDateGreaterThanEqualAndDateLessThanEqual(Date startDate, Date endDate);

    Long countAllByGroupIdAndDateGreaterThanEqualAndDateLessThanEqual(Long id, Date startDate, Date endDate);

    List<Post> findByGroup_Id(Long id);

}
