package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.feed.model.Feed;
import com.web.curation.response.BasicResponse;
import com.web.curation.tag.model.Feedtags;
import com.web.curation.tag.model.Tag;
import com.web.curation.tag.service.TagService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/order")
public class TagController {

	@Autowired
	TagService tagService;
	
	@GetMapping(value = "/bylikes/{keyword}")
	@ApiOperation(value="키워드 입력", notes="해당 키워드 포함 피드 좋아요순 정렬, 같으면 날짜순")
	public ResponseEntity<List<Feed>> orderByLikes(@PathVariable("keyword") String keyword ){
		List<Feed> feeds = tagService.orderByLikes(keyword);
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}

	@GetMapping(value = "/bydate/{keyword}")
	@ApiOperation(value="키워드 입력", notes="해당 키워드 포함 피드 날짜순 정렬")
	public ResponseEntity<List<Feed>> orderByRegtime(@PathVariable("keyword") String keyword ){
		List<Feed> feeds = tagService.orderByRegtime(keyword);
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}
	
//	@GetMapping(value = "/tagranking")
//	@ApiOperation(value="키워드 입력", notes="해당 키워드 태그횟수로 정렬")
//	public ResponseEntity<List<Tags>> orderByTagging(){
//		List<Tags> tagList = tagService.orderByTagCnt();
//		return new ResponseEntity<List<Tags>>(tagList, HttpStatus.OK);
//	}
	
	
//	// 태그 검색하기 feed테이블에서 tag칼럼 안에 태그 있는지 조사해서 Feed반환
//	@GetMapping(value = "/{tagname}")
//	public ResponseEntity<List<Feed>> searchTag(@RequestParam("tagname") String tagname) {
//		List<Feed> feeds = new ArrayList<Feed>();
//		tagService.searchTag(tagname);
//		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
//	}

}
