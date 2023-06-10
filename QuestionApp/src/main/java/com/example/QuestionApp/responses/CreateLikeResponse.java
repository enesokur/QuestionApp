package com.example.QuestionApp.responses;

import com.example.QuestionApp.entities.Like;

import lombok.Data;

@Data
public class CreateLikeResponse {
	private Long id;
	private Long postId;
	private Long userId;
	
	public CreateLikeResponse(Like like) {
		this.id = like.getId();
		this.postId = like.getPost().getId();
		this.userId = like.getUser().getId();
	}
}
