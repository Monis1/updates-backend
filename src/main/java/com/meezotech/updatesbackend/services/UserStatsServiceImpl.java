package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.UserStatsDTO;
import com.meezotech.updatesbackend.domain.User;
import com.meezotech.updatesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserStatsServiceImpl implements UserStatsService {

    private UserRepository userRepository;

    public UserStatsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserStatsDTO getUserStats(Long userId) {
        UserStatsDTO userStatsDTO = new UserStatsDTO();
        User user = userRepository.findOne(userId);
        userStatsDTO.setNumberOfPosts((long) user.getPosts().size());
        userStatsDTO.setNumberOfComments((long) user.getComments().size());
        userStatsDTO.setNumberOfReactions((long) user.getReactions().size());
        return userStatsDTO;
    }

}
