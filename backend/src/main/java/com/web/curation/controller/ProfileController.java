package com.web.curation.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.profile.model.ProfileImage;
import com.web.curation.profile.service.ProfileHandler;
import com.web.curation.profile.service.ProfileService;
import com.web.curation.response.BasicResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "profile")
@CrossOrigin(origins = { "*" })
public class ProfileController {
	@Autowired
	ProfileService profileService;
	
	@GetMapping("/{email}")
	@ApiOperation(value = "프로필 이미지 보기", notes = "이미지 어떻게 봐야되지?")
	public ResponseEntity<BasicResponse> ShowProfileImage(@PathVariable String email){
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		result.object = profileService.findById(email).get();
		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "Fail: 프로필 사진 조회 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@PostMapping("/{email}")
	@ApiOperation(value = "프로필 이미지 저장", notes = "이미 존재시 수정")
	public ResponseEntity<BasicResponse> SaveProfileImage(@PathVariable String email, @RequestBody MultipartFile file){
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		System.out.println(email);
		
//		ProfileImage profile = new ProfileImage();  
//		profile.setEmail(email);
//		profile.setImage_name(file.getOriginalFilename());
//		profile.setContent_type(file.getContentType());
//		profile.setImage_size(file.getSize());
//		profile.setImage_path(file.getResource());
		try {
			result.object = profileService.save(email, file);
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
			result.data = "Fail: 프로필 사진 입력시 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
