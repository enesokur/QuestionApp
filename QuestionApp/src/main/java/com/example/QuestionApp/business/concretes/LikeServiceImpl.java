package com.example.QuestionApp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.QuestionApp.business.abstracts.LikeService;
import com.example.QuestionApp.business.abstracts.PostService;
import com.example.QuestionApp.business.abstracts.UserService;
import com.example.QuestionApp.entities.Like;
import com.example.QuestionApp.entities.Post;
import com.example.QuestionApp.entities.User;
import com.example.QuestionApp.repos.LikeRepository;
import com.example.QuestionApp.requests.CreateLikeRequest;
import com.example.QuestionApp.responses.CreateLikeResponse;
import com.example.QuestionApp.responses.GetLikesResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {
	
	private LikeRepository likeRepository;
	private PostService postService;
	private UserService userService;
	
	@Override
	public List<GetLikesResponse> get(Optional<Long> postId, Optional<Long> userId) {
		List<Like> likesFromDb;
		if(postId.isPresent() && userId.isPresent()) {
			likesFromDb = likeRepository.findByPostIdAndUserId(postId.get(), userId.get());
		}
		else if(postId.isPresent()) {
			likesFromDb = likeRepository.findByPostId(postId.get());
		}
		else if(userId.isPresent()) {
			likesFromDb = likeRepository.findByUserId(userId.get());
		}
		else {
			likesFromDb = likeRepository.findAll();
		}
		List<GetLikesResponse> likeResponse = likesFromDb.stream().map(like -> new GetLikesResponse(like)).collect(Collectors.toList());
		return likeResponse;
	}

	@Override
	public Like getById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

	@Override
	public CreateLikeResponse save(CreateLikeRequest createLikeRequest) {
		Post postFromDb = postService.getById(createLikeRequest.getPostId());
		User userFromDb = userService.getById(createLikeRequest.getUserId());
		if(postFromDb == null || userFromDb == null) {
			return null;
		}
		else {
			Like like = new Like();
			like.setPost(postFromDb);
			like.setUser(userFromDb);
			Like likeToReturn = likeRepository.save(like);
			return new CreateLikeResponse(likeToReturn);
		}
	}

	@Override
	public void delete(Long likeId) {
		likeRepository.deleteById(likeId);
	}
	

}
