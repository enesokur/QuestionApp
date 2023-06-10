package com.example.QuestionApp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.QuestionApp.entities.Post;
import com.example.QuestionApp.requests.CreatePostRequest;
import com.example.QuestionApp.requests.UpdatePostRequest;
import com.example.QuestionApp.responses.CreatePostResponse;
import com.example.QuestionApp.responses.GetPostsResponse;

public interface PostService {
	public List<GetPostsResponse> getAll(Optional<Long> postId);
	public Post getById(Long postId);
	public CreatePostResponse save(CreatePostRequest createPostRequest);
	public Post update(Long postId,UpdatePostRequest updatePostRequest);
	public void delete(Long postId);
}
