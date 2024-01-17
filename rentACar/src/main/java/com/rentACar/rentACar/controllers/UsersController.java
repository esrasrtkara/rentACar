package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.AuthenticationService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserRequest;
import com.rentACar.rentACar.services.dtos.requests.User.SignInRequest;
import com.rentACar.rentACar.services.dtos.requests.User.SignUpRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import com.rentACar.rentACar.services.dtos.responses.JwtAuthenticationResponse;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserListResponse;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public List<GetUserListResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("{id}")
    public GetUserResponse getById(@PathVariable int id){
        return userService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody AddUserRequest request){
        userService.add(request);
    }

    @PutMapping
    public void update(@RequestBody UpdateUserRequest request){
        userService.update(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}