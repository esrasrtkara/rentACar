package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Brand;
import com.rentACar.rentACar.repositories.BrandRepository;
import com.rentACar.rentACar.services.dtos.requests.Brand.AddBrandRequest;
import com.rentACar.rentACar.services.rules.BrandBusinessRules;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class BrandManagerTest {

    private BrandManager brandManager;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Mock
    private BrandRepository brandRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandManager = new BrandManager(brandRepository,modelMapperService,brandBusinessRules);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void  nameWithTwoLettersShouldTrowException(){

        AddBrandRequest addBrandRequest = new AddBrandRequest();
        addBrandRequest.setName("ab");

        assertThrows(RuntimeException.class,()->{
            brandManager.add(addBrandRequest);
        });

    }
    @Test
    void  nameWithOneLettersShouldTrowException(){

        AddBrandRequest addBrandRequest = new AddBrandRequest();
        addBrandRequest.setName("a");

        assertThrows(RuntimeException.class,()->{
            brandManager.add(addBrandRequest);
        });

    }
    @Test
    void  nameWithZeroLettersShouldTrowException(){

        AddBrandRequest addBrandRequest = new AddBrandRequest();
        addBrandRequest.setName("");

        assertThrows(RuntimeException.class,()->{
            brandManager.add(addBrandRequest);
        });

    }

    @Test
    void brandWithSameNameShouldNotExist(){
        AddBrandRequest addBrandRequest = new AddBrandRequest();
        addBrandRequest.setName("Renault");

        Mockito.when(brandRepository.existsByName("Renault"))
                .thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            brandManager.add(addBrandRequest);
        });
    }

}