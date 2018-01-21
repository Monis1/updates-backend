package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    static final String BASE_URL = "/api/v1/users";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUsers(){
        return new UserDTO("Monis", "Ahmed", "monisahmed@gmail.com");
    }

}
