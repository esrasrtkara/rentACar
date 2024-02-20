package com.rentACar.rentACar.services.dtos.responses.Comment;

import lombok.Data;

@Data
public class GetCommentResponse {
    private int id;
    private String text;
    private int userId;
    private int carId;
}
