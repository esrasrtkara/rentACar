package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.services.CloudinaryService;
import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Brand;
import com.rentACar.rentACar.repositories.BrandRepository;
import com.rentACar.rentACar.services.abstracts.BrandService;
import com.rentACar.rentACar.services.constants.Messages;
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
    public DataResult<List<GetBrandListResponse>> getAll() {
        List<Brand> brands = brandRepository.findByDeletedFalse();
        List<GetBrandListResponse> responses = brands.stream().map(brand -> modelMapperService.forResponse()
                .map(brand, GetBrandListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetBrandResponse> getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse response = this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddBrandRequest request) {
        this.brandBusinessRules.checkIfBrandNameExist(request.getName());
        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        brand.setName(request.getName().toUpperCase());
        brandRepository.save(brand);
        return new SuccessResult(Messages.ADDED_BRAND);
    }

    @Override
    public Result update(UpdateBrandRequest request) {
        this.brandBusinessRules.checkIfBrandNameExist(request.getName());
        Brand brand = modelMapperService.forRequest().map(request, Brand.class);
        brand.setName(request.getName().toUpperCase());
        brandRepository.save(brand);
        return new SuccessResult(Messages.UPDATED_BRAND);
    }

    @Override
    public Result delete(int id) {
        Brand brandToDelete = brandRepository.findById(id).orElseThrow();
        brandBusinessRules.modelDeleted(brandToDelete);
        brandRepository.save(brandToDelete);

        return new SuccessResult(Messages.DELETED_BRAND);
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