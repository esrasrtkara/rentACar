package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.TaxRate;
import com.rentACar.rentACar.repositories.TaxRateRepository;
import com.rentACar.rentACar.services.abstracts.TaxRateService;
import com.rentACar.rentACar.services.dtos.requests.TaxRate.AddTaxRateRequest;
import com.rentACar.rentACar.services.dtos.requests.TaxRate.UpdateTaxRateRequest;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class TaxRateManager implements TaxRateService {
    private final TaxRateRepository taxRateRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public List<GetTaxRateListResponse> getAll() {
        List<TaxRate> taxRates = taxRateRepository.findAll();
        List<GetTaxRateListResponse> responses = taxRates.stream().map(taxRate -> modelMapperService.forResponse().map(taxRate,GetTaxRateListResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetTaxRateByIdResponse getById(int id) {
        TaxRate taxRate = taxRateRepository.findById(id).orElseThrow();
        GetTaxRateByIdResponse response = this.modelMapperService.forResponse().map(taxRate,GetTaxRateByIdResponse.class);
        return response;
    }

    @Override
    public void add(AddTaxRateRequest request) {
        TaxRate taxRate = this.modelMapperService.forRequest().map(request,TaxRate.class);
        taxRateRepository.save(taxRate);
    }

    @Override
    public void update(UpdateTaxRateRequest request) {
        TaxRate taxRate = this.modelMapperService.forRequest().map(request,TaxRate.class);
        taxRateRepository.save(taxRate);
    }

    @Override
    public void delete(int id) {
        TaxRate taxRateToDelete = taxRateRepository.findById(id).orElseThrow();
        taxRateRepository.delete(taxRateToDelete);
    }
}
