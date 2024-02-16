package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserListResponse;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;


    @GetMapping
    public DataResult<List<GetUserListResponse>> getAll(){
        return userService.getAll();
    }

    @GetMapping("{id}")
    public DataResult<GetUserResponse> getById(@PathVariable int id){
        return userService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody AddUserRequest request){
        return userService.add(request);
    }

    @PutMapping
    public Result update(@RequestBody UpdateUserRequest request){
        return userService.update(request);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return userService.delete(id);
    }


}