package com.example.QuestionApp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.QuestionApp.entities.Comment;
import com.example.QuestionApp.requests.CreateCommentRequest;
import com.example.QuestionApp.requests.UpdateCommentRequest;
import com.example.QuestionApp.responses.CreateCommentResponse;
import com.example.QuestionApp.responses.GetCommentsResponse;

public interface CommentService {
	public List<GetCommentsResponse> get(Optional<Long> postId, Optional<Long> userId);
	public Comment getById(Long commentId);
	public CreateCommentResponse save(CreateCommentRequest createCommentRequest);
	public Comment update(Long commentId,UpdateCommentRequest updateCommentRequest);
	public void delete(Long commentId);
}
