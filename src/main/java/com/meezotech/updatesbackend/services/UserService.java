package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);

}