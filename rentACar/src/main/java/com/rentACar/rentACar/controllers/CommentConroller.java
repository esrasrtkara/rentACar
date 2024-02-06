package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.CommentService;
import com.rentACar.rentACar.services.dtos.requests.Comment.AddCommentRequest;
import com.rentACar.rentACar.services.dtos.requests.Comment.UpdateCommentRequest;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentListResponse;
import com.rentACar.rentACar.services.dtos.responses.Comment.GetCommentResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
@RestController
@AllArgsConstructor
public class CommentConroller {
    private final CommentService commentService;

    @GetMapping
    List<GetCommentListResponse> getAll(){
        return commentService.getAll();
    }
    @GetMapping("{id}")
    GetCommentResponse getById(@PathVariable int id){
        return commentService.getById(id);
    }
    @PostMapping
    void add(@RequestBody AddCommentRequest request){
        commentService.add(request);
    }
    @PutMapping
    void update(@RequestBody UpdateCommentRequest request){
        commentService.update(request);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable int id){
        commentService.delete(id);
    }
}
