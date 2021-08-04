package com.web.curation.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.badge.model.Badge;
import com.web.curation.badge.service.BadgeService;
import com.web.curation.hobby.model.Hobby;
import com.web.curation.hobby.service.HobbyService;
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
@RequestMapping(value = "/hobby")
public class HobbyController {

	@Autowired
	HobbyService hobbyService;
	
	@Autowired
	BadgeService badgeService;
	
	@PostMapping
	@ApiOperation(value="취미 생성", notes="email, name(취미이름)을 입력받아 uri를 반환")
	public ResponseEntity<?> createHobby(@RequestParam String email, String name) throws URISyntaxException{
		Hobby hobby = hobbyService.save(Hobby.builder()
				.email(email)
				.name(name)
				.build());
		Badge badge = badgeService.save(Badge.builder()
				.name("beginner")
				.hobby(hobby)
				.build());
		List<Badge> badges = new ArrayList<>();
		badges.add(badge);
		hobby.setBadges(badges);
		
		URI uriLocation = new URI("/hobby/" + hobby.getHobbycode());
		return ResponseEntity.created(uriLocation).body("{}");
	}
}
