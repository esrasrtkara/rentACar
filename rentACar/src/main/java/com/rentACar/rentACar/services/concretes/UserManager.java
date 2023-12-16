package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.User;
import com.rentACar.rentACar.repositories.UserRepository;
import com.rentACar.rentACar.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Data
public class UserManager implements UserService {
    private UserRepository userRepository;
    private ModelMapperService modelMapperService;

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
