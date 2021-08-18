package com.web.curation.controller;

import java.net.URI;
import java.util.ArrayList;
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

import com.web.curation.feed.model.Feed;
import com.web.curation.feed.service.FeedService;
import com.web.curation.image.model.Image;
import com.web.curation.scrap.model.Scrap;
import com.web.curation.scrap.service.ScrapService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/scrap")
public class ScrapController {

	@Autowired
	ScrapService scrapService;
	
	@Autowired
	FeedService feedService;
	
	@PostMapping
	@ApiOperation(value="스크랩저장", notes="email과 feedcode를 입력 받아 스크랩 중복 검사 후  uri를 반환")
	public ResponseEntity<?> saveScrap(@RequestParam("email") String email, @RequestParam("feedcode") Integer feedcode) throws Exception{
		
		Boolean flag = scrapService.existsByEmailAndFeedcode(email, feedcode);
		if(!flag) { 
			Scrap scrap = scrapService.save(Scrap.builder()
					.email(email)
					.feedcode(feedcode)
					.build());
			URI uriLocation = new URI("/scrap/"+scrap.getScrapcode());
			return ResponseEntity.created(uriLocation).body("{}");
		}
		else { 
			Scrap scrap = scrapService.findByEmailAndFeedcode(email, feedcode);
			scrapService.deleteByScrapcode(scrap.getScrapcode());
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping
	@ApiOperation(value="스크랩목록", notes="email을 입력 받아 스크랩한 목록을 반환")
	public ResponseEntity<List<Scrap>> findByEmail(@RequestParam("email") String email){
		List<Scrap> scraps = scrapService.findByEmail(email);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < scraps.size(); i++) {
			list.add(scraps.get(i).getFeedcode());
		}
		List<Feed> feeds = scrapService.findByFeedcodeInOrderByRegtimeDesc(list);
		for (int i = 0; i < feeds.size(); i++) {
			Feed feed = feeds.get(i);
			List<Image> images = feedService.findAllByfeedcode(feed.getFeedcode());
			feed.setImages(images);
			scraps.get(i).setFeed(feed);
		}
		return new ResponseEntity<List<Scrap>>(scraps, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{scrapcode}")
	@ApiOperation(value="스크랩삭제", notes="scrapcode을 입력 받아 스크랩 삭제")
	public ResponseEntity<Void> deleteByScrapcode(@PathVariable("scrapcode") Integer scrapcode){
		scrapService.deleteByScrapcode(scrapcode);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}
	
}
