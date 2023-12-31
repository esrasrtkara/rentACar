package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.User.AddUserRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserListResponse;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserResponse;

import java.util.List;

public interface UserService {
    List<GetUserListResponse> getAll();
    GetUserResponse getById(int id);
    void add(AddUserRequest request);
    void update(UpdateUserRequest request);
    void delete(int id);
    boolean controlUserId(int id);
}
