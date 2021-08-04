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

	@GetMapping(value = "/{tagname}")
	@ApiOperation(value = "", notes = "")
	public ResponseEntity<BasicResponse> saveTag(@RequestParam("tagname") String tagname) {

		Tag tagCheck = tagService.check(tagname);
		ResponseEntity<BasicResponse> response = null;
		
		
		if (tagCheck == null) {
			System.out.println("새로운 취미 추가");
			Tag tag = new Tag();
			tag.setTagname(tagname);
			tag.setCnt(1);
			tagService.saveTag(tag);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			System.out.println("이미 있는 취미");
			tagService.updateTagCnt(tagCheck);
			response = new ResponseEntity<>(HttpStatus.OK);		
		}

		return response;

	}

}
