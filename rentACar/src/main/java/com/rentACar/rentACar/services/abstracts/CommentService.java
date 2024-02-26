package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Comment;
import com.rentACar.rentACar.services.dtos.requests.Comment.AddCommentRequest;
import com.rentACar.rentACar.services.dtos.requests.Comment.UpdateCommentRequest;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentListResponse;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerResponse;

import java.util.List;

public interface CommentService {

    public DataResult<List<GetCommentListResponse>> getAll();
    public DataResult<GetCommentResponse> getById(int id);
    public Result add(AddCommentRequest request);
    public Result update(UpdateCommentRequest request);
    public Result delete(int id);

    public List<GetCommentListResponse> getCommentUserId();


}
