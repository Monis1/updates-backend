package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.domain.Gender;
import com.meezotech.updatesbackend.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserMapperImplTest {

    private UserMapper userMapper;
    private static final Long ID = 1L;
    private static final String FIRSTNAME = "Moeed";
    private static final String LASTNAME = "Khan";
    private static final Gender GENDER = Gender.MALE;
    private static final String EMAIL = "moeed@gmail.com";

    @Before
    public void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    public void userDtoToUserTest() {
        //Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(ID);
        userDTO.setFirstName(FIRSTNAME);
        userDTO.setLastName(LASTNAME);
        userDTO.setGender(GENDER.name());
        userDTO.setEmail(EMAIL);

        //when
        User user = userMapper.userDtoToUser(userDTO);

        //then
        assertEquals(FIRSTNAME, user.getFirstName());
        assertEquals(LASTNAME, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(GENDER, user.getGender());
        assertEquals(ID, user.getId());
    }

    @Test
    public void userToUserDtoTest() {
        //Given
        User user = new User();
        user.setId(ID);
        user.setFirstName(FIRSTNAME);
        user.setLastName(LASTNAME);
        user.setGender(GENDER);
        user.setEmail(EMAIL);

        //when
        UserDTO userDTO = userMapper.userToUserDto(user);

        //then
        assertEquals(FIRSTNAME, userDTO.getFirstName());
        assertEquals(LASTNAME, userDTO.getLastName());
        assertEquals(EMAIL, userDTO.getEmail());
        assertEquals(GENDER.name(), userDTO.getGender());
        assertEquals(ID, userDTO.getId());
        assertEquals(0L,(long) userDTO.getFollowers()); // by default followers will be zero
        assertEquals(0L,(long) userDTO.getFollowing()); // by default followings will be zero
        assertEquals(0L,(long) userDTO.getNumberOfPosts()); // by default posts will be zero
    }
}