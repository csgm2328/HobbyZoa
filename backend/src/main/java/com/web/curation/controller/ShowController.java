package com.web.curation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.service.FeedService;
import com.web.curation.follow.service.FollowService;
import com.web.curation.response.BasicResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/show")
public class ShowController {

	@Autowired
	FeedService feedService;
	
	@Autowired
	FollowService followService;
	
	@GetMapping(value="/following")
	@ApiOperation(value = "해당 계정이 팔로우한 모든 피드 조회", notes = "계정 이메일 받아서 팔로우한 계정 피드 반환")
	public ResponseEntity<List<Feed>> getFollowingFeedByEmail(@RequestParam String email){
		List<String> followings = followService.ShowFollowingList(email);
		List<Feed> feeds = feedService.findByEmailInOrderByRegtimeDesc(followings);
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}
	
	@GetMapping(value="/like")
	@ApiOperation(value = "해당 계정이 좋아요한 모든 피드 조회", notes = "계정 이메일 받아서 좋아요한 피드 반환")
	public ResponseEntity<List<Feed>> getLikeFeedByEmail(@RequestParam String email){
		List<Feed> feeds = feedService.getLikeFeedByEmail(email);
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}
	
}
