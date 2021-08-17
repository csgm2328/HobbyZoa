package com.web.curation.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.follow.service.FollowService;
import com.web.curation.profile.service.ProfileService;
import com.web.curation.response.BasicResponse;
import com.web.curation.user.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 500, message = "Bad Request", response = BasicResponse.class) })

@RestController
@RequestMapping(value = "profile")
@CrossOrigin(origins = { "*" })
public class ProfileController {

	@Autowired
	ProfileService profileService;
	@Autowired
	FollowService followService;
	@Autowired
	UserService userService;
	@Autowired
	WSController alarmController;

	@GetMapping("{email}")
	@ApiOperation(value = "프로필 보기", notes = "요청시 게시물 수, 팔로워, 팔로잉 수 업데이트, 코멘트는 계정설정에서 가져옴")
	public ResponseEntity<BasicResponse> ShowProfile(@PathVariable String email) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		result.object = profileService.findProfileById(email);
		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 프로필 조회 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/image/{email}")
	@ApiOperation(value = "프로필 이미지 보기", notes = "이메일당 하나의 프로필 이미지")
	public ResponseEntity<BasicResponse> ShowProfileImage(@PathVariable String email) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		result.object = profileService.findProfileImageById(email).get();
		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 프로필 이미지 조회 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PutMapping("/image/{email}")
	@ApiOperation(value = "프로필 이미지 수정", notes = "프로필은 회원가입시 자동 생성 됨, 프로필에서 수정가능한 건 프로필 이미지 뿐")
	public ResponseEntity<BasicResponse> SaveProfileImage(@PathVariable String email, @RequestBody MultipartFile file) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		System.out.println(email + "님 프로필 수정됨");

		try {
			result.object = profileService.saveImage(email, file);
		} catch (IllegalStateException | IOException e) {
			System.out.println("FAIL: 업로드 파일 오류");
			e.printStackTrace();
		}
		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 프로필 이미지 입력시 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/follow")
	@ApiOperation(value = "팔로우 기능", notes = "이미 팔로워라면 언팔로우됨")
	public ResponseEntity<BasicResponse> Follow(
			@RequestParam(required = true) @ApiParam(value = "email") final String from,
			@RequestParam(required = true) @ApiParam(value = "email") final String to) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		if (!userService.findById(from).isPresent() || !userService.findById(to).isPresent() || from.equals(to)) {
			result.status = false;
			result.data = "fail: 등록된 유저만 팔로우를 할 수 있습니다. 혹은 올바르지 않은 팔로우 요청입니다";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}
		result.object = followService.Follow(from, to);
		result.status = true;
		if (result.object != null)
			result.data = "success:" + "[" + from + "] 가 [" + to + "]를 팔로우 했습니다.";
		else 
			result.data = "success:" + "[" + from + "] 가 [" + to + "]를 더이상 팔로우하지 않습니다.";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/checkfollow")
	@ApiOperation(value = "팔로우 여부 확인", notes = "방문한 사람이 현재 유저를 팔로우하는 중인지 체크")
	public ResponseEntity<BasicResponse> CheckFollow(
			@RequestParam(required = true) @ApiParam(value = "email") final String from,
			@RequestParam(required = true) @ApiParam(value = "email") final String to) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();

		if (followService.Check(from, to)) {
			result.status = true;
			result.data = "[" + from + "] 가 [" + to + "]를 팔로우 중입니다.";
		} else {
			result.status = false;
			result.data = "[" + from + "] 가 [" + to + "]를 팔로우 중이 아닙니다.";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/followerlist/{email}")
	@ApiOperation(value = "팔로워 리스트 보기", notes = "입력한 email 유저를 팔로우하는 from_email 리스트")
	public ResponseEntity<BasicResponse> ShowFollowerList(@PathVariable String email) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		result.object = followService.ShowFollowerList(email);
		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 팔로워 리스트  조회 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/followinglist/{email}")
	@ApiOperation(value = "팔로잉 리스트 보기", notes = "입력한 email 유저가 팔로우하는 to_email 리스트")
	public ResponseEntity<BasicResponse> ShowFollowingList(@PathVariable String email) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		result.object = followService.ShowFollowingList(email);
		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 팔로잉 리스트 조회 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
