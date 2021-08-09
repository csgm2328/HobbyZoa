package com.web.curation.controller;

import java.util.ArrayList;
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
import com.web.curation.trend.service.TrendService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/orderby")
public class TrendController {
	
	@Autowired
	TrendService trendService;
	
	@GetMapping(value = "/likes/{keyword}")
	@ApiOperation(value="키워드 입력", notes="해당 키워드 포함 피드 좋아요순 정렬, 같으면 날짜순")
	public ResponseEntity<List<Feed>> orderByLikes(@RequestParam("keyword") String keyword ){
		List<Feed> feeds = trendService.orderByLikes(keyword);
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
		
	}

	@GetMapping(value = "/date/{keyword}")
	@ApiOperation(value="키워드 입력", notes="해당 키워드 포함 피드 날짜순 정렬")
	public ResponseEntity<List<Feed>> orderByRegtime(@RequestParam("keyword") String keyword ){
		List<Feed> feeds = trendService.orderByRegtime(keyword);
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
		
	}
}
