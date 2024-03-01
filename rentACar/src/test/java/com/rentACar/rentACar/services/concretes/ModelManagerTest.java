package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.repositories.ModelRepository;
import com.rentACar.rentACar.services.dtos.requests.Model.AddModelRequest;
import com.rentACar.rentACar.services.rules.ModelBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest {

    private ModelManager modelManager;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;


    @Mock
    private ModelRepository modelRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modelManager = new ModelManager(modelRepository,modelMapperService,modelBusinessRules);
    }
   @Test
    void nameWithOneLettersShouldThrowException(){
        AddModelRequest addModelRequest = new AddModelRequest();
        addModelRequest.setName("a");

        assertThrows(RuntimeException.class,()->{
            modelManager.add(addModelRequest);
        });

    }
    @Test
    void nameWithZeroLettersShouldThrowException(){
        AddModelRequest addModelRequest = new AddModelRequest();
        addModelRequest.setName("");

        assertThrows(RuntimeException.class,()->{
            modelManager.add(addModelRequest);
        });

    }
}