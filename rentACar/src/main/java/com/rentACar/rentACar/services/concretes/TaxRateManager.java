package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.TaxRate;
import com.rentACar.rentACar.repositories.TaxRateRepository;
import com.rentACar.rentACar.services.abstracts.TaxRateService;
import com.rentACar.rentACar.services.constants.Messages;
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
    public DataResult<List<GetTaxRateListResponse>> getAll() {
        List<TaxRate> taxRates = taxRateRepository.findAll();
        List<GetTaxRateListResponse> responses = taxRates.stream().map(taxRate -> modelMapperService.forResponse().map(taxRate,GetTaxRateListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetTaxRateByIdResponse> getById(int id) {
        TaxRate taxRate = taxRateRepository.findById(id).orElseThrow();
        GetTaxRateByIdResponse response = this.modelMapperService.forResponse().map(taxRate,GetTaxRateByIdResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddTaxRateRequest request) {
        TaxRate taxRate = this.modelMapperService.forRequest().map(request,TaxRate.class);
        taxRateRepository.save(taxRate);
        return new SuccessResult(Messages.ADDED_TAX_RATE);
    }

    @Override
    public Result update(UpdateTaxRateRequest request) {
        TaxRate taxRate = this.modelMapperService.forRequest().map(request,TaxRate.class);
        taxRateRepository.save(taxRate);
        return new SuccessResult(Messages.UPDATED_TAX_RATE);
    }

    @Override
    public Result delete(int id) {
        TaxRate taxRateToDelete = taxRateRepository.findById(id).orElseThrow();
        taxRateRepository.delete(taxRateToDelete);
        return new SuccessResult(Messages.DELETED_TAX_RATE);
    }
}
