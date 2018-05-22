package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import com.meezotech.updatesbackend.api.v1.model.UserListDTO;
import com.meezotech.updatesbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/user";

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO createUser(@RequestBody UserDTO userDTO, @RequestParam("token") String token) {
        return userService.createUser(userDTO, token);
    }

    @GetMapping("/single")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@RequestParam("userId") long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDTO getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/group/admin")
    @ResponseStatus(HttpStatus.OK)
    public UserListDTO getAllGroupUsers(@RequestParam("groupId") Long groupId) {
        return userService.getAllGroupUsers(groupId);
    }

    @PutMapping("/ban/status/admin")
    @ResponseStatus(HttpStatus.OK)
    public void changeGroupBanStatus(@RequestParam("groupId") Long groupId,
                                     @RequestParam("userId") Long userId,
                                     @RequestParam("isBanned") boolean isBanned) {
        userService.changeBanStatus(groupId, userId, isBanned);
    }

    @GetMapping("/post/reaction")
    @ResponseStatus(HttpStatus.OK)
    public UserListDTO getAllUsersReactedToThisPost(@RequestParam("postId") Long postId) {
        return userService.getAllUsersReactedToThisPost(postId);
    }

    @DeleteMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    public boolean logout(@RequestParam("userId") Long userId) {
        return userService.logout(userId);
    }

}
