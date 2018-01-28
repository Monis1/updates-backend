package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.UserMapper;
import com.meezotech.updatesbackend.api.v1.mapper.UserMapperImpl;
import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.domain.Gender;
import com.meezotech.updatesbackend.domain.User;
import com.meezotech.updatesbackend.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private static final Long ID = 1L;

    private UserService userService;
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userMapper = new UserMapperImpl();
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    public void createUserTest() {
        // mocking repository to return no record
        Optional<User> userOptional = Optional.empty();
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Monis");
        userDTO.setLastName("Khan");
        userDTO.setEmail("m@mz.com");
        userDTO.setGender(Gender.MALE.name());

        // mocking the repository to save user object
        when(userRepository.save(any(User.class))).thenReturn(userMapper.userDtoToUser(userDTO));

        // check if the returned object is not null
        UserDTO userDtoReturned = userService.createUser(userDTO);
        assertNotNull("Null User Returned", userDtoReturned);
    }

    @Test
    public void getUserById() {
        User user = new User();
        user.setId(ID);
        user.setFirstName("Monis");
        user.setLastName("khan");
        user.setGender(Gender.MALE);
        user.setEmail("monisahmed8@gmail.com");

        // mocking the repository to get user object
        when(userRepository.findOne(ID)).thenReturn(user);

        // check if the returned object is not null
        UserDTO userDtoReturned = userService.getUserById(ID);
        assertNotNull("Null User Returned", userDtoReturned);
    }
}