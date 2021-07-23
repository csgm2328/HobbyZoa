package com.web.curation.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<?> createFeed(
            @Valid @RequestParam("email") String email,
            @Valid @RequestParam("nickname") String nickname,
            @RequestParam("comment") String comment,
            @Valid @RequestPart("files") MultipartFile files
    ) throws Exception {
		List<MultipartFile> list = new ArrayList(); //테스트를 위한 코드, swagger ui는 여러파일 업로드 지원하지 않아서 
		list.add(files);
        Feed feed = feedService.save(Feed.builder()
                .email(email)
                .nickname(nickname)
                .comment(comment)
                .build(), list);

        URI uriLocation = new URI("/board/" + feed.getFeedcode());
        return ResponseEntity.created(uriLocation).body("{}");
    }
		

	// 모든 피드 조회 
	@GetMapping(value="/all") 
	public ResponseEntity<List<Feed>> getAllFeeds() { 
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
