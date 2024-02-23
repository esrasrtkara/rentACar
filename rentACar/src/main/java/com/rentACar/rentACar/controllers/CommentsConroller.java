package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Comment;
import com.rentACar.rentACar.services.abstracts.CommentService;
import com.rentACar.rentACar.services.dtos.requests.Comment.AddCommentRequest;
import com.rentACar.rentACar.services.dtos.requests.Comment.UpdateCommentRequest;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentListResponse;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
@RestController
@AllArgsConstructor
@CrossOrigin
public class CommentsConroller {
    private final CommentService commentService;

    @GetMapping
    DataResult<List<GetCommentListResponse>> getAll(){
        return commentService.getAll();
    }
    @GetMapping("{id}")
    DataResult<GetCommentResponse> getById(@PathVariable int id){
        return commentService.getById(id);
    }
    @PostMapping
    Result add(@RequestBody @Valid AddCommentRequest request){
        return commentService.add(request);
    }
    @PutMapping
    Result update(@RequestBody @Valid UpdateCommentRequest request){
        return commentService.update(request);
    }
    @DeleteMapping("{id}")
    Result delete(@PathVariable int id){
        return commentService.delete(id);
    }

    @GetMapping("userId")
    public List<GetCommentListResponse> getCommentUserId(){
        return commentService.getCommentUserId();
    }

}
