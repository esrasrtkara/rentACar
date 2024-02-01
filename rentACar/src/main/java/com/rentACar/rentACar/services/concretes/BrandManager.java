package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Brand;
import com.rentACar.rentACar.repositories.BrandRepository;
import com.rentACar.rentACar.services.abstracts.BrandService;
import com.rentACar.rentACar.services.dtos.requests.Brand.AddBrandRequest;
import com.rentACar.rentACar.services.dtos.requests.Brand.UpdateBrandRequest;
import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandListResponse;
import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandResponse;
import com.rentACar.rentACar.services.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetBrandListResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetBrandListResponse> responses = brands.stream().map(brand -> modelMapperService.forResponse()
                .map(brand, GetBrandListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse response = this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
        return response;
    }

    @Override
    public void add(AddBrandRequest request) {
        this.brandBusinessRules.checkIfBrandNameExist(request.getName());
        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        brand.setName(request.getName().toUpperCase());
        brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest request) {
        this.brandBusinessRules.checkIfBrandNameExist(request.getName());
        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        brand.setName(request.getName().toUpperCase());
        brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        Brand brandToDelete = brandRepository.findById(id).orElseThrow();
        brandRepository.delete(brandToDelete);
    }

    @Override
    public boolean controlBrandId(int id) {
        try {
            brandRepository.findById(id).orElseThrow();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }
}