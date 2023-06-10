package com.example.QuestionApp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.QuestionApp.entities.Like;
import com.example.QuestionApp.requests.CreateLikeRequest;
import com.example.QuestionApp.responses.CreateLikeResponse;
import com.example.QuestionApp.responses.GetLikesResponse;

public interface LikeService {
	public List<GetLikesResponse> get(Optional<Long> postId, Optional<Long> userId);
	public Like getById(Long likeId);
	public CreateLikeResponse save(CreateLikeRequest createLikeRequest);
	public void delete(Long likeId);

}
