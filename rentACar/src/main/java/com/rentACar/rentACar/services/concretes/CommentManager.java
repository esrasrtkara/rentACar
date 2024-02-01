package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Comment;
import com.rentACar.rentACar.repositories.CommentRepository;
import com.rentACar.rentACar.services.abstracts.CommentService;
import com.rentACar.rentACar.services.dtos.requests.Comment.AddCommentRequest;
import com.rentACar.rentACar.services.dtos.requests.Comment.UpdateCommentRequest;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentListResponse;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapperService modelMapperService;
    @Override
    public List<GetCommentListResponse> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentListResponse> responses = comments.stream().map(comment -> modelMapperService.forResponse().map(comment, GetCommentListResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetCommentResponse getById(int id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        GetCommentResponse response = modelMapperService.forResponse().map(comment,GetCommentResponse.class);
        return response;
    }

    @Override
    public void add(AddCommentRequest request) {
        Comment comment = modelMapperService.forRequest().map(request,Comment.class);
        commentRepository.save(comment);
    }

    @Override
    public void update(UpdateCommentRequest request) {
        Comment comment = modelMapperService.forRequest().map(request,Comment.class);
        commentRepository.save(comment);

    }

    @Override
    public void delete(int id) {
        Comment commentToDelete = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(commentToDelete);
    }
}
