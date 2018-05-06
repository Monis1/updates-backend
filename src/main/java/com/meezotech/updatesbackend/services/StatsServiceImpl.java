package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.StatsDTO;
import com.meezotech.updatesbackend.domain.Group;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.User;
import com.meezotech.updatesbackend.repositories.GroupRepository;
import com.meezotech.updatesbackend.repositories.PostRepository;
import com.meezotech.updatesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class StatsServiceImpl implements StatsService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private PostRepository postRepository;

    public StatsServiceImpl(UserRepository userRepository, GroupRepository groupRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
    }

    @Override
    public StatsDTO getUserStats(Long userId) {
        StatsDTO statsDTO = new StatsDTO();
        User user = userRepository.findOne(userId);
        statsDTO.setNumberOfPosts(user.getPosts().size());
        statsDTO.setNumberOfComments(user.getComments().size());
        statsDTO.setNumberOfReactions(user.getReactions().size());
        return statsDTO;
    }

    @Override
    public StatsDTO getOverAllStats() {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setNumberOfUsers(userRepository.count());
        statsDTO.setNumberOfPosts(postRepository.count());
        long noOfComments = 0;
        long noOfReactions = 0;
        for (Post post :
                postRepository.findAll()) {
            noOfComments += post.getComments().size();
            noOfReactions += post.getReactions().size();
        }
        statsDTO.setNumberOfComments(noOfComments);
        statsDTO.setNumberOfReactions(noOfReactions);
        return statsDTO;
    }

    @Override
    public StatsDTO getMonthlyStats() {
        StatsDTO statsDTO = new StatsDTO();
        LocalDate today = LocalDate.now();
        Date startDate = java.sql.Date.valueOf(today.withDayOfMonth(1));
        Date endDate = java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));
        statsDTO.setNumberOfUsers(
                userRepository.countAllByJoiningDateGreaterThanEqualAndJoiningDateLessThanEqual(startDate, endDate));
        statsDTO.setNumberOfPosts(
                postRepository.countAllByDateGreaterThanEqualAndDateLessThanEqual(startDate, endDate));
        return statsDTO;
    }

    @Override
    public StatsDTO getOverAllGroupStats(Long groupId) {
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setNumberOfUsers(userRepository.count());
        Group group = groupRepository.findOne(groupId);
        statsDTO.setNumberOfPosts(group.getPosts().size());
        long noOfComments = 0;
        long noOfReactions = 0;
        for (Post post :
                group.getPosts()) {
            noOfComments += post.getComments().size();
            noOfReactions += post.getReactions().size();
        }
        statsDTO.setNumberOfComments(noOfComments);
        statsDTO.setNumberOfReactions(noOfReactions);
        return statsDTO;
    }

    @Override
    public StatsDTO getMonthlyGroupStats(Long groupId) {
        StatsDTO statsDTO = new StatsDTO();
        LocalDate today = LocalDate.now();
        Date startDate = java.sql.Date.valueOf(today.withDayOfMonth(1));
        Date endDate = java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));
        statsDTO.setNumberOfPosts(postRepository.countAllByGroupIdAndDateGreaterThanEqualAndDateLessThanEqual
                (groupId, startDate, endDate));
        statsDTO.setNumberOfUsers(
                userRepository.countAllByJoiningDateGreaterThanEqualAndJoiningDateLessThanEqual(startDate, endDate));
        return statsDTO;
    }

}
