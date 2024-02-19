package com.rentACar.rentACar.services.dtos.requests.Comment;

import lombok.Data;

@Data
public class AddCommentRequest {
    private String text;
    private int carId;
}
