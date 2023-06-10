package com.example.QuestionApp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.QuestionApp.business.abstracts.CommentService;
import com.example.QuestionApp.business.abstracts.PostService;
import com.example.QuestionApp.business.abstracts.UserService;
import com.example.QuestionApp.entities.Comment;
import com.example.QuestionApp.entities.Post;
import com.example.QuestionApp.entities.User;
import com.example.QuestionApp.repos.CommentRepository;
import com.example.QuestionApp.requests.CreateCommentRequest;
import com.example.QuestionApp.requests.UpdateCommentRequest;
import com.example.QuestionApp.responses.CreateCommentResponse;
import com.example.QuestionApp.responses.GetCommentsResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	@Override
	public List<GetCommentsResponse> get(Optional<Long> postId, Optional<Long> userId) {
		List<Comment> commentsFromDb;
		if(postId.isPresent() && userId.isPresent()) {
			commentsFromDb = commentRepository.findByPostIdAndUserId(postId.get(), userId.get());
		}
		else if(postId.isPresent()) {
			commentsFromDb = commentRepository.findByPostId(postId.get());
		}
		else if(userId.isPresent()) {
			commentsFromDb = commentRepository.findByUserId(userId.get());
		}
		else {
			commentsFromDb = commentRepository.findAll();
		}
		List<GetCommentsResponse> commentResponse = commentsFromDb.stream().map(comment -> new GetCommentsResponse(comment)).collect(Collectors.toList());
		return commentResponse;
	}

	@Override
	public Comment getById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	@Override
	public CreateCommentResponse save(CreateCommentRequest createCommentRequest) {
		User userFromDb = userService.getById(createCommentRequest.getUserId());
		Post postFromDb = postService.getById(createCommentRequest.getPostId());
		if(userFromDb == null || postFromDb == null) {
			return null;
		}
		else{
			Comment comment = new Comment();
			comment.setUser(userFromDb);
			comment.setPost(postFromDb);
			comment.setText(createCommentRequest.getText());
			Comment commentToReturn = commentRepository.save(comment);
			return new CreateCommentResponse(commentToReturn);
		}
	}

	@Override
	public Comment update(Long commentId, UpdateCommentRequest updateCommentRequest) {
		Optional<Comment> commentFromDb = commentRepository.findById(commentId);
		if(commentFromDb.isPresent()) {
			Comment commentFound = commentFromDb.get();
			commentFound.setText(updateCommentRequest.getText());
			return commentRepository.save(commentFound);
		}
		else {
			return null;
		}
	}

	@Override
	public void delete(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}
