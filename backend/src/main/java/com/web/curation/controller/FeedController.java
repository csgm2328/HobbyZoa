package com.web.curation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.service.FeedService;
import com.web.curation.response.BasicResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { 
		@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) 
		})
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/feed")
public class FeedController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FeedService feedService;
	
	// 피드 생성
	@PostMapping 
	public ResponseEntity<Feed> save(Feed feed) { 
		return new ResponseEntity<Feed>(feedService.save(feed), HttpStatus.OK); 
	}

	// 모든 피드 조회 
	@GetMapping(value="/all") 
	public ResponseEntity<List<Feed>> getAllfeeds() { 
		List<Feed> feed = feedService.findAll(); 
		return new ResponseEntity<List<Feed>>(feed, HttpStatus.OK); 
	}
	
	// 피드번호로 피드 삭제
	@DeleteMapping(value = "/{feedcode}") 
	public ResponseEntity<Void> deleteFeed(@PathVariable("feedcode") Integer feedcode) { 
		feedService.deleteByFeedcode(feedcode); 
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}
}
