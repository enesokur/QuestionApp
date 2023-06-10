package com.example.QuestionApp.responses;

import java.util.List;

import com.example.QuestionApp.entities.Like;
import com.example.QuestionApp.entities.Post;
import lombok.Data;

@Data
public class GetPostsResponse {
	private Long id;
	private Long userId;
	private String userName;
	private String title;
	private String text;
	private List<GetLikesResponse> likes;
	
	public GetPostsResponse(Post post, List<GetLikesResponse> likesForPost) {
		this.id = post.getId();
		this.userId = post.getUser().getId();
		this.userName = post.getUser().getUserName();
		this.title = post.getTitle();
		this.text = post.getText();
		this.likes = likesForPost;
	}
}
