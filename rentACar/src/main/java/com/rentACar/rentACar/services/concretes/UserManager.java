package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.repositories.UserRepository;
import com.rentACar.rentACar.services.abstracts.CustomerService;
import com.rentACar.rentACar.services.abstracts.RentalService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserListResponse;
import com.rentACar.rentACar.services.dtos.responses.User.GetUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapperService modelMapperService;


    @Override
    public DataResult<List<GetUserListResponse>> getAll() {
        List<User> users = userRepository.findAll();
        List<GetUserListResponse> responses = users.stream().map(user -> modelMapperService.forResponse()
                .map(user, GetUserListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetUserResponse> getById(int id) {
        User user = userRepository.findById(id).orElseThrow();
        GetUserResponse response = this.modelMapperService.forResponse().map(user ,GetUserResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddUserRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
        return new SuccessResult(Messages.ADDED_USER);
    }

    @Override
    public Result update(UpdateUserRequest request) {
      //  User user = modelMapperService.forRequest().map(request, User.class);
        //userRepository.save(user);
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).build();
                user.setId(request.getId());
        userRepository.save(user);
        return new SuccessResult(Messages.UPDATED_USER);
    }

    @Override
    public Result delete(int id) {
        User userToDelete = userRepository.findById(id).orElseThrow();
        userRepository.delete(userToDelete);
        return new SuccessResult(Messages.DELETED_USER);
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

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User userEmail(String email) {
        User user =userRepository.findByEmail(email).orElseThrow();
        return user;
    }

    @Override
    public int userId(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
        return user.getId();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not Found"));
    }




}