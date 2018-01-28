package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.UserMapper;
import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.domain.User;
import com.meezotech.updatesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) { // if user already exists, returns the user
            return userMapper.userToUserDto(userOptional.get());
        }
        return userMapper.userToUserDto(userRepository.save(user)); // save and return
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findOne(id));
    }
}
