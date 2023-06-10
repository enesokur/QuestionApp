package com.example.QuestionApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuestionApp.business.abstracts.CommentService;
import com.example.QuestionApp.entities.Comment;
import com.example.QuestionApp.requests.CreateCommentRequest;
import com.example.QuestionApp.requests.UpdateCommentRequest;
import com.example.QuestionApp.responses.CreateCommentResponse;
import com.example.QuestionApp.responses.GetCommentsResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
	private CommentService commentService;
	
	@GetMapping()
	public List<GetCommentsResponse> getAllComments(@RequestParam("postId") Optional<Long> postId, @RequestParam("userId") Optional<Long> userId){
		return commentService.get(postId,userId);
	}
	
	@GetMapping("/{commentId}")
	public Comment getCommentById(@PathVariable Long commentId) {
		return commentService.getById(commentId);
	}
	
	@PostMapping()
	public CreateCommentResponse createComment(@RequestBody CreateCommentRequest createCommentRequest) {
		return commentService.save(createCommentRequest);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateCommentById(@PathVariable Long commentId,@RequestBody UpdateCommentRequest updateCommentRequest) {
		return commentService.update(commentId,updateCommentRequest);
	}
	
	@DeleteMapping("/commentId")
	public void deleteById(@PathVariable Long commentId) {
		commentService.delete(commentId);
	}
}
