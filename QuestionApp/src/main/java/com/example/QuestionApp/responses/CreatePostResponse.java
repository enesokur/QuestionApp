package com.example.QuestionApp.responses;

import java.util.ArrayList;
import java.util.List;

import com.example.QuestionApp.entities.Post;

import lombok.Data;

@Data
public class CreatePostResponse {
	private Long id;
	private Long userId;
	private String userName;
	private String title;
	private String text;
	private List<GetLikesResponse> likes;
	
	public CreatePostResponse(Post post) {
		this.id = post.getId();
		this.userId = post.getUser().getId();
		this.userName = post.getUser().getUserName();
		this.title = post.getTitle();
		this.text = post.getText();
		likes = new ArrayList<GetLikesResponse>();
	}
}
