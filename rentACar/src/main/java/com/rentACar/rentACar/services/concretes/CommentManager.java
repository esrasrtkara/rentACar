package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.Comment;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.repositories.CommentRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.abstracts.CommentService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Comment.AddCommentRequest;
import com.rentACar.rentACar.services.dtos.requests.Comment.UpdateCommentRequest;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentListResponse;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapperService modelMapperService;
    private final UserService userService;
    @Override
    public DataResult<List<GetCommentListResponse>> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentListResponse> responses = comments.stream().map(comment -> modelMapperService.forResponse().map(comment, GetCommentListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetCommentResponse> getById(int id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        GetCommentResponse response = modelMapperService.forResponse().map(comment,GetCommentResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddCommentRequest request) {
        User user =userService.userEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Comment comment = modelMapperService.forRequest().map(request,Comment.class);
        comment.setUser(user);
        commentRepository.save(comment);
        return new SuccessResult(Messages.ADDED_COMMENT);
    }

    @Override
    public Result update(UpdateCommentRequest request) {
        Comment comment = modelMapperService.forRequest().map(request,Comment.class);
        commentRepository.save(comment);
        return new SuccessResult(Messages.UPDATED_COMMENT);
    }

    @Override
    public Result delete(int id) {
        Comment commentToDelete = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(commentToDelete);
        return new SuccessResult(Messages.DELETED_COMMENT);
    }


    public List<GetCommentListResponse> getCommentUserId(){
        int userId =userService.userId(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Comment> comments = commentRepository.findByUserId(userId);
        List<GetCommentListResponse> responses = comments.stream().map(comment -> modelMapperService.forResponse().map(comment,GetCommentListResponse.class))
                .collect(Collectors.toList());
        return  responses;
    }


}