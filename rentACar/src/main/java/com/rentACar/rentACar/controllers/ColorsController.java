package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.dtos.requests.Color.AddColorRequest;
import com.rentACar.rentACar.services.dtos.requests.Color.UpdateColorRequest;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorListResponse;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/colors")
@AllArgsConstructor
public class ColorsController {
    private final ColorService colorService;

    @GetMapping
    public List<GetColorListResponse> getAll(){
        return colorService.getAll();
    }

    @GetMapping("{id}")
    public GetColorResponse getById(@PathVariable int id){
        return colorService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid AddColorRequest request){
        colorService.add(request);
    }

    @PutMapping
    public void update(@RequestBody UpdateColorRequest request){
        colorService.update(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        colorService.delete(id);
    }
}
