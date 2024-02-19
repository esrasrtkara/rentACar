package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.dtos.requests.Color.AddColorRequest;
import com.rentACar.rentACar.services.dtos.requests.Color.UpdateColorRequest;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorListResponse;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor
@CrossOrigin
public class ColorsController {
    private final ColorService colorService;

    @GetMapping
    public DataResult<List<GetColorListResponse>> getAll(){
        return colorService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetColorResponse> getById(@PathVariable int id){
        return colorService.getById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result add(@RequestBody @Valid AddColorRequest request){
        return colorService.add(request);
    }

    @PutMapping
    @ResponseStatus(code =  HttpStatus.OK)
    public Result update(@RequestBody UpdateColorRequest request){
        return colorService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result delete(@PathVariable int id){
        return colorService.delete(id);
    }
}
