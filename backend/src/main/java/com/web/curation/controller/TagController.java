package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.feed.model.Feed;
import com.web.curation.response.BasicResponse;
import com.web.curation.tag.model.Tag;
import com.web.curation.tag.service.TagService;

import io.swagger.annotations.ApiOperation;

//태그 생성,검색(찾고 카운트 증가)
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/tag")
public class TagController {

	@Autowired
	TagService tagService;

	// 태그 검색하기 feed테이블에서 tag칼럼 안에 태그 있는지 조사해서 Feed반환
	@GetMapping(value = "/{tagname}")
	public List<Feed> searchTag(@RequestParam("tagname") String tagname) {
		List<Feed> feeds = new ArrayList<Feed>();
		tagService.searchTag(tagname);
		return feeds;
	}

}
