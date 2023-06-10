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
import com.example.QuestionApp.business.abstracts.PostService;
import com.example.QuestionApp.entities.Post;
import com.example.QuestionApp.requests.CreatePostRequest;
import com.example.QuestionApp.requests.UpdatePostRequest;
import com.example.QuestionApp.responses.CreatePostResponse;
import com.example.QuestionApp.responses.GetPostsResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
	
	private PostService postService;
	
	@GetMapping()
	public List<GetPostsResponse> getAllPosts(@RequestParam("userId") Optional<Long> userId){
		return postService.getAll(userId);
	}
	
	@GetMapping("/{postId}")
	public Post getPostById(@PathVariable Long postId) {
		return postService.getById(postId);
	}
	
	@PostMapping()
	public CreatePostResponse createPost(@RequestBody CreatePostRequest createPostRequest) {
		return postService.save(createPostRequest);
	}
	
	@PutMapping("/{postId}")
	public Post updatePostById(@PathVariable Long postId,@RequestBody UpdatePostRequest updatePostRequest) {
		return postService.update(postId,updatePostRequest);
	}
	
	@DeleteMapping("/{postId}")
	public void deleteById(@PathVariable Long postId) {
		postService.delete(postId);
	}
	
	
}
