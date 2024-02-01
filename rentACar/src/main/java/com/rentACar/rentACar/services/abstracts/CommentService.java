package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.Comment.AddCommentRequest;
import com.rentACar.rentACar.services.dtos.requests.Comment.UpdateCommentRequest;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentListResponse;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentResponse;

import java.util.List;

public interface CommentService {

    public List<GetCommentListResponse> getAll();
    public GetCommentResponse getById(int id);
    public void add(AddCommentRequest request);
    public void update(UpdateCommentRequest request);
    public void delete(int id);
}
