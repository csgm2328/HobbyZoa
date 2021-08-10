package com.web.curation.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.alarm.model.MessageType;
import com.web.curation.alarm.service.AlarmService;
import com.web.curation.feed.model.Feed;
import com.web.curation.feed.service.FeedService;
import com.web.curation.image.model.Image;
import com.web.curation.response.BasicResponse;
import com.web.curation.scrap.model.Scrap;
import com.web.curation.scrap.service.ScrapService;

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
@RequestMapping(value = "/scrap")
public class ScrapController {

	@Autowired
	ScrapService scrapService;
	
	@Autowired
	FeedService feedService;
	@Autowired
	AlarmService alarmService;
	
	@PostMapping
	@ApiOperation(value="스크랩저장", notes="email과 feedcode를 입력 받아 스크랩 중복 검사 후  uri를 반환")
	public ResponseEntity<?> saveScrap(@RequestParam("email") String email, @RequestParam("feedcode") Integer feedcode) throws Exception{
		
		Boolean flag = scrapService.existsByEmailAndFeedcode(email, feedcode);
		if(!flag) { //중복 없으면 저장
			Scrap scrap = scrapService.save(Scrap.builder()
					.email(email)
					.feedcode(feedcode)
					.build());
			URI uriLocation = new URI("/scrap/"+scrap.getScrapcode());
			Feed feed = feedService.findByFeedcode(feedcode);
			String alarmMsg = "[" + email + "]님이 회원님의 "+ feedcode + "번 피드를 스크랩했습니다.";
			alarmService.sendAlarm(MessageType.SCRAP, email, feed.getEmail(), alarmMsg);
			return ResponseEntity.created(uriLocation).body("{}");
		}
		else { //이미 있으면 스크랩 해제
			Scrap scrap = scrapService.findByEmailAndFeedcode(email, feedcode);
			scrapService.deleteByScrapcode(scrap.getScrapcode());
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping
	@ApiOperation(value="스크랩목록", notes="email을 입력 받아 스크랩한 목록을 반환")
	public ResponseEntity<List<Scrap>> findByEmail(@RequestParam("email") String email){
		List<Scrap> scraps = scrapService.findByEmail(email);
		for (int i = 0; i < scraps.size(); i++) {
			Feed feed = feedService.findByFeedcode(scraps.get(i).getFeedcode());
			List<Image> images = feedService.findAllByfeedcode(feed.getFeedcode());
			feed.setImages(images);
			scraps.get(i).setFeed(feed);
		}
		return new ResponseEntity<List<Scrap>>(scraps, HttpStatus.OK);
	}
	
//	@GetMapping(value="/{scrapcode}")
//	@ApiOperation(value="스크랩 보기", notes="스크랩한 피드 반환")
//	public void findByScrapcode(@PathVariable("scrapcode") Integer scrapcode){
//		
//	}
	
	@DeleteMapping(value="/{scrapcode}")
	@ApiOperation(value="스크랩삭제", notes="scrapcode을 입력 받아 스크랩 삭제")
	public ResponseEntity<Void> deleteByScrapcode(@PathVariable("scrapcode") Integer scrapcode){
		scrapService.deleteByScrapcode(scrapcode);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}
	
	
}
