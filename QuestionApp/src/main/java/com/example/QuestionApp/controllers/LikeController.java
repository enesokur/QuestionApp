package com.example.QuestionApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.QuestionApp.business.abstracts.LikeService;
import com.example.QuestionApp.entities.Like;
import com.example.QuestionApp.requests.CreateLikeRequest;
import com.example.QuestionApp.responses.CreateLikeResponse;
import com.example.QuestionApp.responses.GetLikesResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/likes")
@AllArgsConstructor
public class LikeController {
	private LikeService likeService;
	
	@GetMapping()
	public List<GetLikesResponse> getAllLikes(@RequestParam("postId") Optional<Long> postId, @RequestParam Optional<Long> userId){
		return likeService.get(postId,userId);
	}
	
	@GetMapping("/{likeId}")
	public Like getLikeById(@PathVariable Long likeId) {
		return likeService.getById(likeId);
	}
	
	@PostMapping()
	public CreateLikeResponse createLike(@RequestBody CreateLikeRequest createLikeRequest) {
		return likeService.save(createLikeRequest);
	}
	
	@DeleteMapping("/{likeId}")
	public void deleteById(@PathVariable Long likeId) {
		likeService.delete(likeId);
	}
}
