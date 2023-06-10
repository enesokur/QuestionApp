package com.example.QuestionApp.responses;

import com.example.QuestionApp.entities.Like;

import lombok.Data;

@Data
public class GetLikesResponse {
	private Long id;
	private Long postId;
	private Long userId;
	
	public GetLikesResponse(Like like) {
		this.id = like.getId();
		this.postId =like.getPost().getId();
		this.userId = like.getUser().getId();
	}
}
