package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.repositories.UserRepository;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserListResponse;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class UserManager implements UserService {
    private UserRepository userRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetUserListResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<GetUserListResponse> responses = users.stream().map(user -> modelMapperService.forResponse()
                .map(user, GetUserListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetUserResponse getById(int id) {
        User user = userRepository.findById(id).orElseThrow();
        GetUserResponse response = this.modelMapperService.forResponse().map(user ,GetUserResponse.class);
        return response;
    }

    @Override
    public void add(AddUserRequest request) {
        User user = modelMapperService.forRequest().map(request, User.class);
        userRepository.save(user);
    }

    @Override
    public void update(UpdateUserRequest request) {
        User user = modelMapperService.forRequest().map(request, User.class);
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        User userToDelete = userRepository.findById(id).orElseThrow();
        userRepository.delete(userToDelete);
    }

    @Override
    public boolean controlUserId(int id) {
        try {
            User user = userRepository.findById(id).orElseThrow();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}