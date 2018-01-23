package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.domain.User;

public interface UserMapper {

    User userDtoToUser(UserDTO userDTO);

    UserDTO userToUserDto(User user);

}
