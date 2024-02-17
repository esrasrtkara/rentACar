package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.dtos.requests.Model.AddModelRequest;
import com.rentACar.rentACar.services.dtos.requests.Model.UpdateModelRequest;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelListResponse;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
@CrossOrigin
public class ModelsController {

    private final ModelService modelService;

    @GetMapping
    public DataResult<List<GetModelListResponse>> getAll(){
    return modelService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetModelResponse> getById(@PathVariable int id){
        return modelService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result add(@RequestBody @Valid AddModelRequest request){
        return modelService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Result update(@RequestBody @Valid UpdateModelRequest request){
        return modelService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id){
        return modelService.delete(id);
    }
}
