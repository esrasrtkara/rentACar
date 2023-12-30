package com.rentACar.rentACar.services.dtos.requests.Model;

import lombok.Data;

@Data
public class UpdateModelRequest {
    private int id;
    private String name;
    private int brandId;
}
