package com.rentACar.rentACar.services.dtos.requests.Comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCommentRequest {
    @NotNull
    private String text;
    private int carId;
}
