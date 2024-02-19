package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserListResponse;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    DataResult<List<GetUserListResponse>> getAll();
    DataResult<GetUserResponse> getById(int id);
    Result add(AddUserRequest request);
    Result update(UpdateUserRequest request);
    Result delete(int id);
    boolean controlUserId(int id);
    void save(User user);

    User userEmail(String email);

    public int userId(String email);


}
