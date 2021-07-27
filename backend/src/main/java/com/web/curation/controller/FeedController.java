package com.web.curation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.service.FeedService;
import com.web.curation.image.model.Image;
import com.web.curation.reply.model.Reply;
import com.web.curation.reply.service.ReplyService;
import com.web.curation.response.BasicResponse;

import io.swagger.annotations.ApiOperation;
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
	
	@Autowired
	ReplyService replyService;
	
	// 피드 생성
	@PostMapping
	@ApiOperation(value="피드 생성", notes="email, nickname, comment와 file리스트를 입력받아 uri를 반환")
	public ResponseEntity<?> createFeed(
            @Valid @RequestParam("email") String email,
            @Valid @RequestParam("nickname") String nickname,
            @RequestParam("comment") String comment,
            @Valid @RequestPart("files") List<MultipartFile> files
    ) throws Exception { //swagger는 위에 List<MultipartFile> 대신 MultipartFile로 
        Feed feed = feedService.save(Feed.builder()
                .email(email)
                .nickname(nickname)
                .comment(comment)
                .build(), files);

        URI uriLocation = new URI("/feed/" + feed.getFeedcode());
        return ResponseEntity.created(uriLocation).body("{}");
    }
		

	// 모든 피드 조회 (이미지 엮어서 보내주기)
	@GetMapping(value="/all") 
	@ApiOperation(value="모든 피드 조회", notes="모든 피드 반환")
	public ResponseEntity<List<Feed>> getAllFeeds() { 
		List<Feed> feeds = feedService.findAllFeeds();
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK); 
	}
	
	// 해당 계정 피드 조회 
	@GetMapping(value="/{email}") 
	@ApiOperation(value="해당 계정의 모든 피드 조회", notes="피드 comment와 이미지1장 반환")
	public ResponseEntity<Map<String, Image>> getFeedsByEmail(@PathVariable("email") String email) { 
		Map<String, Image> map = new HashMap<>();
		//email에 해당하는 모든 피드 list
		List<Feed> list = feedService.findByEmail(email);
		for (int i = 0; i < list.size(); i++) {
			Feed curFeed = list.get(i);
			Image image = feedService.findOneByfeedcode(curFeed.getFeedcode());
			map.put(curFeed.getComment(), image);
		}
		return new ResponseEntity<Map<String, Image>>(map, HttpStatus.OK); 
	}
	
	// 해당 feedcode 피드 상세보기
	@GetMapping(value="/search/{feedcode}") 
	@ApiOperation(value="해당 feedcode의 피드 상세보기", notes="피드 정보 담긴 객체와 이미지리스트 반환")
	public ResponseEntity<Map<String, Object>> getFeedByFeedcode(@PathVariable("feedcode") Integer feedcode) { 
		Map<String, Object> map = new HashMap<>();
		Feed feed = feedService.findByFeedcode(feedcode);
		List<Image> images = feedService.findAllByfeedcode(feedcode);
		List<Reply> replies = replyService.findAllByFeedcode(feedcode);
		map.put("feed", feed);
		map.put("images", images);
		map.put("replies", replies);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK); 
	}
	
	// 피드번호로 피드 삭제
	@DeleteMapping(value = "/{feedcode}") 
	@ApiOperation(value="피드 삭제", notes="feedcode로 삭제")
	public ResponseEntity<Void> deleteFeed(@PathVariable("feedcode") Integer feedcode) { 
		feedService.deleteByFeedcode(feedcode); 
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}
	
	// 피드 번호로 피드수정
	@PutMapping(value = "/{feedcode}") 
	@ApiOperation(value="피드 수정", notes="feedcode로 수정 후 수정 피드 반환")
	public ResponseEntity<Feed> updateFeed(@PathVariable("feedcode") Integer feedcode, 
			Feed feed, @RequestPart("files") List<MultipartFile> files) throws Exception { 
		feedService.updateByFeedcode(feedcode, feed, files); 		
		return new ResponseEntity<Feed>(feed, HttpStatus.OK); 
	}
	
	//
	@GetMapping(value="{newname}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> imageSearch(@PathVariable("newname") String newname)throws IOException {
		Image image = feedService.findByNewname(newname);
		InputStream imageStream = new FileInputStream("C:\\subpjt2Img\\"+image.getImgpath());
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
}
