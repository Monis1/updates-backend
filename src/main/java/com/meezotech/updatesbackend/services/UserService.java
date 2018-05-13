package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.api.v1.model.UserListDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserListDTO getAllUsers();
    UserListDTO getAllGroupUsers(Long groupId);
    void changeBanStatus(Long groupId, Long userId, boolean isBanned);
    UserListDTO getAllUsersReactedToThisPost(Long postId);

}
