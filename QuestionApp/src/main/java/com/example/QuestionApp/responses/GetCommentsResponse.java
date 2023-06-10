package com.example.QuestionApp.responses;

import com.example.QuestionApp.entities.Comment;

import lombok.Data;

@Data
public class GetCommentsResponse {
	private Long id;
	private String text;
	private String userName;
	private Long userId;
	
	public GetCommentsResponse(Comment comment) {
		this.id = comment.getId();
		this.text = comment.getText();
		this.userName = comment.getUser().getUserName();
		this.userId = comment.getUser().getId();
	}
	
}
