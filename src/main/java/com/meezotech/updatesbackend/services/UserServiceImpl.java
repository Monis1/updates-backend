package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.UserMapper;
import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.api.v1.model.UserListDTO;
import com.meezotech.updatesbackend.domain.Group;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.Reaction;
import com.meezotech.updatesbackend.domain.User;
import com.meezotech.updatesbackend.repositories.GroupRepository;
import com.meezotech.updatesbackend.repositories.PostRepository;
import com.meezotech.updatesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private UserMapper userMapper;
    private PostRepository postRepository;

    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository, UserMapper userMapper, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.userMapper = userMapper;
        this.postRepository = postRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO, String token) {
        User user = userMapper.userDtoToUser(userDTO);
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) { // if user already exists, returns the user
            user.setId(userOptional.get().getId());
        } else {
            user.setJoiningDate(new Date());
        }
        user.setNotificationToken(token);
        return userMapper.userToUserDto(userRepository.save(user)); // save and return
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO userDTO = userMapper.userToUserDto(userRepository.findOne(id));
        long count = userRepository.count();
        userDTO.setFollowing(count);
        userDTO.setFollowers(count);
        return userDTO;
    }

    @Override
    public UserListDTO getAllUsers() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user :
                users) {
            userDTOS.add(userMapper.userToUserDto(user));
        }
        return new UserListDTO(userDTOS);
    }

    @Override
    public UserListDTO getAllGroupUsers(Long groupId) {
        Iterable<User> users = userRepository.findAll();
        Group group = groupRepository.findOne(groupId);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user :
                users) {
            UserDTO userDTO = userMapper.userToUserDto(user);
            userDTO.setBanned(user.getGroups().contains(group));
            userDTOS.add(userDTO);
        }
        return new UserListDTO(userDTOS);
    }

    @Override
    public void changeBanStatus(Long groupId, Long userId, boolean isBanned) {
        User user = userRepository.findOne(userId);
        Group group = groupRepository.findOne(groupId);
        boolean isAlreadyBanned = user.getGroups().contains(group);
        if (isBanned && !isAlreadyBanned) {
            user.getGroups().add(group);
            group.getBannedUsers().add(user);
        } else if (!isBanned && isAlreadyBanned) {
            user.getGroups().remove(group);
            group.getBannedUsers().remove(user);
        }
        userRepository.save(user);
    }

    @Override
    public UserListDTO getAllUsersReactedToThisPost(Long postId) {
        List<UserDTO> userDTOS = new ArrayList<>();
        Post post = postRepository.findOne(postId);
        for (Reaction reaction :
                post.getReactions()) {
            userDTOS.add(userMapper.userToUserDto(reaction.getUser()));
        }
        return new UserListDTO(userDTOS);
    }

    @Override
    public boolean logout(Long userId) {
        User user = userRepository.findOne(userId);
        user.setNotificationToken("");
        userRepository.save(user);
        return true;
    }


}
