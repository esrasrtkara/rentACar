package com.rentACar.rentACar.services.dtos.requests.Comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCommentRequest {
    private int id;
    @NotNull
    private String text;
    private int userId;
    private int carId;
}
