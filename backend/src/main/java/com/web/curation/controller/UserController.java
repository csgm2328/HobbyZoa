package com.web.curation.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.user.service.UserService;
import com.web.curation.email.service.EmailTokenServiceImpl;
import com.web.curation.response.BasicResponse;
import com.web.curation.user.model.SignupRequest;
import com.web.curation.user.model.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	EmailTokenServiceImpl confirmationTokenService;

	@GetMapping("/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<User> userOpt = userService.findUserByEmailAndPassword(email, password);
		ResponseEntity response = null;

		if (userOpt.isPresent()) {
			System.out.println("[ " + userOpt.get().getNickname() + " ] 님 로그인 성공");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			result.object = userOpt.get();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		System.out.println(response);
		return response;
	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입", notes = "비밀번호는 문자,숫자,특문포함해서  8자리 이상")

	public Object signup(@Valid @RequestBody SignupRequest request) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		if (!userService.findById(request.getEmail()).isPresent()) { //이메일 중복검사
			User user = new User();
			user.setNickname(request.getNickname());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setPhone(request.getPhone());
			user.setComment(request.getComment());

			userService.save(user);
			System.out.println("[ " + user.getNickname() + " ] 님 등록 성공");
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			// 회원가입 후 이메일 인증 하기:
			// 토큰생성(token save) --> 토큰ID와 함께 이메일 인증 링크 전송 -->
			// 만료전(5분) 링크 접속시 인증완료 --> 인증된 이메일로 처리
			confirmationTokenService.createEmailConfirmationToken(request.getEmail(), request.getEmail());
		} else {
			result.status = true;
			result.data = "Fail: 이미 존재하는 이메일입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("confirm-email")
	@ApiOperation(value = "회원가입시 이메일 인증", notes = "5분 안에 링크접속시 인증완료 페이지 제공")
	public Object ConfirmUserEmail(@Valid @RequestParam String token) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		if (userService.confirmEmail(token)) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = true;
			result.data = "Fail: 이메일 인증 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("reconfirm-email")
	@ApiOperation(value = "이메일 재인증", notes = "토큰 만료로 인한 재인증 요청")
	public Object reConfirmEmail(@RequestParam(required = true, value = "가입한 이메일") final String userEmail,
			@RequestParam(required = true, value = "인증메일 받을 이메일") final String recieverEmail) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		// 기존 토큰 삭제 후 재 생성
		userService.reCreateToken(userEmail, recieverEmail);
		result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
}