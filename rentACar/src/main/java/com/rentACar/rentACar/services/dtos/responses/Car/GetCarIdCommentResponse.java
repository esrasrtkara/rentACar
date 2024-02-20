package com.rentACar.rentACar.services.dtos.responses.Car;

import lombok.Data;

@Data
public class GetCarIdCommentResponse {
    private int id;
    private String text;
    private int userId;
}
