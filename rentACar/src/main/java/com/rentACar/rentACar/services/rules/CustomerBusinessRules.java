package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerBusinessRules {
    private final UserService userService;
    public void checkIfUserId(int id){
        if(!userService.controlUserId(id)){
            throw new RuntimeException(Messages.CHECK_IF_USER_ID);
        }
    }
}
