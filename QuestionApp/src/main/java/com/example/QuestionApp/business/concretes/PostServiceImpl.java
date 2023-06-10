package com.example.QuestionApp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.QuestionApp.business.abstracts.LikeService;
import com.example.QuestionApp.business.abstracts.PostService;
import com.example.QuestionApp.business.abstracts.UserService;
import com.example.QuestionApp.entities.Like;
import com.example.QuestionApp.entities.Post;
import com.example.QuestionApp.entities.User;
import com.example.QuestionApp.repos.PostRepository;
import com.example.QuestionApp.requests.CreatePostRequest;
import com.example.QuestionApp.requests.UpdatePostRequest;
import com.example.QuestionApp.responses.CreatePostResponse;
import com.example.QuestionApp.responses.GetLikesResponse;
import com.example.QuestionApp.responses.GetPostsResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	
	@Override
	public List<GetPostsResponse> getAll(Optional<Long> userId) {
		List<Post> postListFromDb;
		if(userId.isPresent()) {
			postListFromDb = postRepository.findByUserId(userId.get());
		}
		else {
			postListFromDb = postRepository.findAll();
		}
		List<GetPostsResponse> postResponse = postListFromDb.stream().map(post -> {
			List<Like> likesForPost = post.getLikes();
			List<GetLikesResponse> likesResponse = likesForPost.stream().map(like -> new GetLikesResponse(like)).collect(Collectors.toList());
			return new GetPostsResponse(post,likesResponse);
		}).collect(Collectors.toList());
		return postResponse;

	}

	@Override
	public Post getById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	@Override
	public CreatePostResponse save(CreatePostRequest createPostRequest) {
		User userfromdb = userService.getById(createPostRequest.getUserId());
		if(userfromdb == null) {
			return null;
		}
		else {
			Post post = new Post();
			post.setUser(userfromdb);
			post.setTitle(createPostRequest.getTitle());
			post.setText(createPostRequest.getText());
			Post postToReturn = postRepository.save(post);
			return new CreatePostResponse(postToReturn); 
		}
	}

	@Override
	public Post update(Long postId, UpdatePostRequest updatePostRequest) {
		Optional<Post> postFromDb = postRepository.findById(postId);
		if(postFromDb.isPresent()) {
			Post post = postFromDb.get();
			post.setText(updatePostRequest.getText());
			post.setTitle(updatePostRequest.getTitle());
			return postRepository.save(post);
		}
		else {
			return null;
		}
	}

	@Override
	public void delete(Long postId) {
		postRepository.deleteById(postId);
	}
	
}
