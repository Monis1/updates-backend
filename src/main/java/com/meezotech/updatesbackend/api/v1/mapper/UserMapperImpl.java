package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.domain.Gender;
import com.meezotech.updatesbackend.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User userDtoToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setGender(Gender.valueOf(userDTO.getGender()));
        user.setProfilePictureUrl(userDTO.getProfilePictureUrl());

        return user;
    }

    @Override
    public UserDTO userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setGender(user.getGender().name());
        userDTO.setProfilePictureUrl(user.getProfilePictureUrl());
        userDTO.setFollowers((long) user.getFollowedUsers().size());
        userDTO.setFollowing((long) user.getFollowingUsers().size());
        userDTO.setNumberOfPosts((long) user.getPosts().size());

        return userDTO;
    }
}
