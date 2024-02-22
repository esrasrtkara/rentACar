package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin
public class UsersController {
    private final UserService userService;

    @GetMapping
    public int getUserId(){
        return userService.getUserId();
    }
}
