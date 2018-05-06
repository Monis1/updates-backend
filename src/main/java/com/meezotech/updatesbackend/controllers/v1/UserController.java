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
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/admin")
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

}
